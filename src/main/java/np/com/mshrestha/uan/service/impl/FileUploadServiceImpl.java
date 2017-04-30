package np.com.mshrestha.uan.service.impl;

import np.com.mshrestha.uan.service.FileUploadService;
import np.com.mshrestha.uan.dao.FileUploadDao;
import np.com.mshrestha.uan.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {

  //private SessionFactory sessionFactory;

  @Autowired
  private FileUploadDao dao;

 /* public FileUploadServiceImpl(){}
  public FileUploadServiceImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;

  }*/

  @Override
  @Transactional(readOnly = true)
  public List<UploadedFile> listFiles() {

    return dao.listFiles();
  }


  @Override
  @Transactional(readOnly = true)
  public UploadedFile getFile(Long id) {
    return dao.getFile(id);
  }

  @Override
  @Transactional
  public UploadedFile saveFile(UploadedFile uploadedFile) {
    return dao.saveFile(uploadedFile);

  }

    @Override
    @Transactional
    public void createUser(String nameSession) {
        dao.createUser(nameSession);
    }

    @Override
    @Transactional
    public void saveParsedPhoto(List<UploadedFile> activeFilesInSession){
        dao.saveParsedPhoto(activeFilesInSession);
    }


    @Override
    @Transactional
    public String parseStatus(UploadedFile activeFileInSession){
        return dao.parseStatus(activeFileInSession);
    }


}
