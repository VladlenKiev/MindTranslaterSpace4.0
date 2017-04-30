package np.com.mshrestha.uan.dao.impl;


import np.com.mshrestha.uan.dao.AssociateUploadDao;
import np.com.mshrestha.uan.model.Associate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AssociateUploadDaoImpl implements AssociateUploadDao {

  @PersistenceContext
  private EntityManager entityManager;
  @Autowired
  public EntityManager getEntityManager(){
    return entityManager;
  }

  @PersistenceContext
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }








  @Transactional
  public Associate getAssociate(Long id) {
    return getEntityManager().find(Associate.class, id);
  }

  @Transactional
  public Associate saveAssociate(Associate associate) {
    return getEntityManager().merge(associate);
  }
}
