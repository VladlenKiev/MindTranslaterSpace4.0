package np.com.mshrestha.uan.service;

import np.com.mshrestha.uan.model.Associate;


public interface AssociateUploadService {

  Associate getAssociate(Long id);

  Associate saveAssociate(Associate associate);

}
