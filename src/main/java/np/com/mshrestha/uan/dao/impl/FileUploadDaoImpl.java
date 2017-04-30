package np.com.mshrestha.uan.dao.impl;

import java.util.List;

import np.com.mshrestha.uan.Parser.SearchBoxParser;
import np.com.mshrestha.uan.dao.FileUploadDao;
import np.com.mshrestha.uan.model.CV;
import np.com.mshrestha.uan.model.UploadedFile;

import np.com.mshrestha.uan.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class FileUploadDaoImpl implements FileUploadDao {

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


  @SuppressWarnings("unchecked")
  @Transactional
  public List<UploadedFile> listFiles() {
    //return getSession().createCriteria(UploadedFile.class).list();
    //getEntityManager().getTransaction().begin();
    //Query query = getEntityManager().createQuery("Select uf from UploadedFile u WHERE u.nameSession LIKE :nameSess").setParameter("nameSess",nameSession);
    Query query = getEntityManager().createQuery("Select uf from UploadedFile uf");
    List<UploadedFile> resList = query.getResultList();

    return resList;
  }

    @Transactional
    public void saveParsedPhoto(List<UploadedFile> ActiveFilesInSession){
        loadParsedPhoto(ActiveFilesInSession);
    }

  @Transactional
  public UploadedFile getFile(Long id) {
    //return (UploadedFile) getSession().get(UploadedFile.class, id);
    //getEntityManager().getTransaction().begin();
    UploadedFile uploadedFile = getEntityManager().find(UploadedFile.class, id);
    return uploadedFile;
  }

  @Transactional
  public UploadedFile saveFile(UploadedFile uploadedFile) {
    return getEntityManager().merge(uploadedFile); //persist?
  }


    @Transactional
    public void createUser(String nameSession){
        Query usersBySession = getEntityManager().createQuery("SELECT us FROM Users us where us.name LIKE :nameSess").setParameter("nameSess",nameSession);
        List<Users> usersList =  usersBySession.getResultList();
        if (usersList.size() == 0){
            Users users = new Users(nameSession, "n/a");
            getEntityManager().persist(users);
            return;
        }
        System.out.println("NOT created! BEACUSE user is exist");
     //   Users users = new Users(nameSession, "n/a");
       // getEntityManager().persist(users);
    }

    @Transactional
    private Long getIDbySession(String nameSession){
        System.out.println("nameSess for FIND "+nameSession);
        Query usersBySession = getEntityManager().createQuery("SELECT us FROM Users us where us.name LIKE :nameSess").setParameter("nameSess",nameSession);
        Users users = (Users) usersBySession.getSingleResult();
        Long idUsersbySess = (Long)users.getId();
        System.out.println("from getIDbySess "+idUsersbySess);
        return idUsersbySess;//in LONG -exe: 2L
    }

    @Transactional
    private void loadParsedPhoto(List<UploadedFile> activeFilesInSession){
        String nameSession = activeFilesInSession.get(0).getNameSession();

        SearchBoxParser.CVparser(activeFilesInSession); //ready CV in CLASS
        List<CV> cvList = SearchBoxParser.getcvList();
        //getEntityManager().persist(contactList.get(0).getcv());

        for (CV cv:cvList){
            System.out.println("save to db 1");
            System.out.println("save to db 3");

            System.out.println("4.5");
            getEntityManager().persist(cv);
            System.out.println("save to db 4");

            Long tempID = getIDbySession(nameSession);
            addCV(tempID,cv);
            System.out.println("save to db 5");
        }
        //getEntityManager().flush();
    }

    @Transactional
    private void addCV(Long idUSER, CV cv) {
        Users user = getEntityManager().find(Users.class,idUSER);
        user.addCV(cv);
        getEntityManager().persist(user);
    }


    @Transactional
    public String parseStatus(UploadedFile activeFileInSession){
        return SearchBoxParser.parseStatus(activeFileInSession);
    }
}
