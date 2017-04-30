package np.com.mshrestha.uan.controller;

import np.com.mshrestha.uan.model.UploadedFile;
import np.com.mshrestha.uan.service.AssociateUploadService;
import np.com.mshrestha.uan.service.FileUploadService;
import np.com.mshrestha.uan.model.Associate;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FileUploadController {

  @Autowired
  private AssociateUploadService associateUploadService;

  @Autowired
  private FileUploadService uploadService;


  private static List<UploadedFile> actFiles;

  private static List<UploadedFile> activeFilesInSession;

  public static List<UploadedFile> getActiveFilesInSession() {
    return activeFilesInSession;
  }

  public static void setActiveFilesInSession(List<UploadedFile> activeFilesInSession) {
    FileUploadController.activeFilesInSession = activeFilesInSession;
  }

  @RequestMapping(value = {"/photoAnalyzer"})
  public String photoAnalyzer(HttpServletRequest request) {
    //System.out.println("/fileUploader being called by");
    //String sessionID = request.getSession().getId();
    //userUploadService.createAnonymousUser(sessionID);
    //System.out.println("/fileUploader sesID from ="+sessionID);

    // System.out.println(Integer.valueOf(request.getParameter("phID")));
    //saveAssociateToDatabase(makeResultAssociate(Integer.valueOf(request.getParameter("phID")), request.getParameter("associate"), Long.valueOf(request.getSession().getId())));
    return "/photoAnalyzer";
  }
  @RequestMapping(value = {"/goPhotoAnalyzer"})
  public String goPhotoAnalyzer(HttpServletRequest request) {
    //System.out.println("/fileUploader being called by");
    //String sessionID = request.getSession().getId();
    //userUploadService.createAnonymousUser(sessionID);
    //System.out.println("/fileUploader sesID from ="+sessionID);
    System.out.println("associate="+request.getParameter("associate"));
    System.out.println("phID="+request.getParameter("phID"));
    System.out.println("try to save");
    Associate associate = makeResultAssociate(Integer.valueOf(request.getParameter("phID")), request.getParameter("associate"), request.getSession().getId());
    System.out.println("phID from associate="+associate.getPhID());
    saveAssociateToDatabase(makeResultAssociate(Integer.valueOf(request.getParameter("phID")), request.getParameter("associate"), request.getSession().getId()));
    return "/photoAnalyzer";
  }

  private Associate saveAssociateToDatabase(Associate associate) {


    return associateUploadService.saveAssociate(associate);
  }
  private Associate makeResultAssociate(Integer phID, String description, String sessID) {
    Associate associate = new Associate();
    associate.setPhID(phID);
    associate.setAssociate(description);
    associate.setSessionID(sessID);
    return associate;
  }



  @RequestMapping(value = "/")
  public String home(HttpServletRequest request) {
    String nameSession = request.getSession().getId();

    uploadService.createUser(nameSession);

    return "/photoAnalyzer";
  }

  @RequestMapping(value = "/uploaded", method = RequestMethod.POST) //value = "/upload",
  public @ResponseBody List<UploadedFile> upload(MultipartHttpServletRequest request,
      HttpServletResponse response) throws IOException {
    String nameSession = request.getSession().getId();

    // Getting uploaded files from the request object
    Map<String, MultipartFile> fileMap = request.getFileMap();

    // Maintain a list to send back the files info. to the client side
    List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();

    // Iterate through the map
    for (MultipartFile multipartFile : fileMap.values()) {
      if (confirmType((getTypeFile(multipartFile)))) {
        // Save the file to local disk
        saveFileToLocalDisk(multipartFile);

        UploadedFile fileInfo = getUploadedFileInfo(multipartFile,true,nameSession);
        //activeFilesInSession.add(fileInfo);

        // Save the file info to database
        fileInfo = saveFileToDatabase(fileInfo); //load fileINFO to DB!!!!!!!!!!!!

        // adding the file info to the list
        uploadedFiles.add(fileInfo);
      }else
        uploadedFiles.add(getUploadedFileInfo(multipartFile,false,nameSession));
    }
    setActiveFilesInSession(getUploadedFiles(uploadedFiles));
    return uploadedFiles;
  }



  @RequestMapping(value = "/parse", method = RequestMethod.POST) //value = "/upload",
  public @ResponseBody String parse(HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
    //String nameSession = request.getSession().getId();
    String nameSession = "These files:"+"\n";
    System.out.println("PARSED "+nameSession);
    response.setHeader("HeadSessionID", request.getSession().getId());
    //System.out.println("size= "+getActiveFilesInSession().size());
    for (int i =0; i<getActiveFilesInSession().size();i++){
      nameSession = nameSession + "   - "+getActiveFilesInSession().get(i).getName()+
              uploadService.parseStatus(getActiveFilesInSession().get(i))+"\n";
    }
    System.out.println("size of CV: " + getActiveFilesInSession().size());
    System.out.println("SEND command to SAVE CV");
    //saveCVToDatabase(getActiveFilesInSession()); //load CV to DB!!!!!!!!!!!!
    uploadService.saveParsedPhoto(getActiveFilesInSession());
    //-------------- clean List<UploadedFile> activeFilesInSession
    setActiveFilesInSession(new ArrayList<UploadedFile>());
    //----------------------------------------------------
    System.out.println("RECIEVE command to SAVE CV");
    return nameSession;
  }


  @RequestMapping(value = {"/list"})
  public String listBooks(Map<String, Object> map) {
    //String nameSession = request.getSession().getId();
    map.put("fileList", uploadService.listFiles());

    return "/listFiles";
  }

  @RequestMapping(value = {"/fileUploader"})
  public String fileUploader() {
    //String nameSession = request.getSession().getId();
    //map.put("fileList", uploadService.listFiles());

    return "/fileUploader";
  }

  @RequestMapping(value = "/get/{fileId}", method = RequestMethod.GET)
  public void getFile(HttpServletResponse response, @PathVariable Long fileId) {

    UploadedFile dataFile = uploadService.getFile(fileId);

    File file = new File(dataFile.getLocation(), dataFile.getName());

    try {
      response.setContentType(dataFile.getType());
      response.setHeader("Content-disposition", "attachment; filename=\"" + dataFile.getName()
          + "\"");

      FileCopyUtils.copy(FileUtils.readFileToByteArray(file), response.getOutputStream());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String getTypeFile(MultipartFile multipartFile){
    String fileNameSplit[]=multipartFile.getOriginalFilename().split("\\.");
    return fileNameSplit[fileNameSplit.length-1];
  }

  private Boolean confirmType(String typeFile){
    String[] types=new String[]{"img","jpg","jpeg","doc","pdf"};
    for(String type:types){
      if(type.equals(typeFile)) return true;
    }
    return false;
  }

  private void saveFileToLocalDisk(MultipartFile multipartFile) throws IOException,
      FileNotFoundException {

    String outputFileName = getOutputFilename(multipartFile);

    FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(outputFileName));
  }

  private UploadedFile saveFileToDatabase(UploadedFile uploadedFile) {

    return uploadService.saveFile(uploadedFile);
  }



  private String getOutputFilename(MultipartFile multipartFile) {

    return getDestinationLocation() + multipartFile.getOriginalFilename();
  }

  private UploadedFile getUploadedFileInfo(MultipartFile multipartFile,boolean status,String name) throws IOException {

    UploadedFile fileInfo = new UploadedFile();
    fileInfo.setName(multipartFile.getOriginalFilename());
    fileInfo.setSize(multipartFile.getSize());
    fileInfo.setType(multipartFile.getContentType());
    fileInfo.setLocation(getDestinationLocation());
    fileInfo.setNameSession(name);

    fileInfo.setStatus(status?"upload":"not loaded");
    return fileInfo;
  }

  private String getDestinationLocation() {
    return "C:/uploaded-files/";
  }

  //show info in dialog after btn-parsed
  private static List<UploadedFile> getUploadedFiles(List<UploadedFile> uploadedFileList){
    List<UploadedFile> finalList = new ArrayList<>();
    for (UploadedFile file:uploadedFileList){
      if (file.getStatus().equals("upload"))
        finalList.add(file);
    }
    if (finalList.size()==0) {
      UploadedFile emptyFile = new UploadedFile();
      emptyFile.setName("NOUN");
      finalList.add(emptyFile);
    }
    return finalList;
  }
 }
