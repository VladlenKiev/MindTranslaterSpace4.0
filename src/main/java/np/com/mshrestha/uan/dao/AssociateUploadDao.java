package np.com.mshrestha.uan.dao;

import np.com.mshrestha.uan.model.Associate;

public interface AssociateUploadDao {

  Associate getAssociate(Long id);

  Associate saveAssociate(Associate associate);


}
