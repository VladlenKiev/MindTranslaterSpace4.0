package np.com.mshrestha.uan.service;

import np.com.mshrestha.uan.model.UploadedFile;

import java.util.List;

public interface FileUploadService {

  List<UploadedFile> listFiles();

  UploadedFile getFile(Long id);

  UploadedFile saveFile(UploadedFile uploadedFile);


  void createUser(String nameSession);

  void saveParsedPhoto(List<UploadedFile> activeFilesInSession);

  //----------------------------------------
  String parseStatus(UploadedFile activeFileInSession);
}
