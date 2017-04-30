package np.com.mshrestha.uan.service.impl;

import np.com.mshrestha.uan.dao.AssociateUploadDao;
import np.com.mshrestha.uan.service.AssociateUploadService;
import np.com.mshrestha.uan.model.Associate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssociateUploadServiceImpl implements AssociateUploadService {

    //private SessionFactory sessionFactory;

    @Autowired
    private AssociateUploadDao dao;

 /* public FileUploadServiceImpl(){}
  public FileUploadServiceImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;

  }*/


    @Override
    @Transactional(readOnly = true)
    public Associate getAssociate(Long id) {
        return dao.getAssociate(id);
    }

    @Override
    public Associate saveAssociate(Associate associate) {
        return dao.saveAssociate(associate);
    }


}
