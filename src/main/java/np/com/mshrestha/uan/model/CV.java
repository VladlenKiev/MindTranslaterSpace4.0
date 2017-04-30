package np.com.mshrestha.uan.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 13.09.2016.
 */

@Entity
@Table(name = "cves")
@NamedQueries({
        @NamedQuery(name = "cves.findAll", query = "SELECT cv FROM CV cv"),
        @NamedQuery(name = "cves.findByID", query = "SELECT cv FROM CV cv where id=:idReq")
})
public class CV {
    private long id;
    private String skills;
    private String exp;
    private String lang;
    private String edu;
    private String trainings;

    private Date date;
    private Users usersByFK;

    public CV(){}
    public CV(String skills,String exp, String lang, String edu, Date date,String trainings){
        this.skills = skills;
        this.exp = exp;
        this.lang = lang;
        this.date = date;
        this.edu = edu;
        this.trainings=trainings;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cv", unique = true, nullable = false)
    public long getId() {
        return id;
    }



    //CV has Cont_ID


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "User_id", nullable = true)
    public Users getUsersByFK() {
        return usersByFK;
    }

    public Date getDate() {
        return this.date;
    }
    @Column(name = "Skills", nullable = false, length = 5000)
    public String getSkills() {
        return skills;
    }
    @Column(name = "Expierence", nullable = true, length = 5000)
    public String getExp() {
        return exp;
    }
    @Column(name = "Language", nullable = true, length = 5000)
    public String getLang() {
        return lang;
    }
    @Column(name = "Education", nullable = true, length = 5000)
    public String getEdu() {
        return edu;
    }
    @Column(name="Trainings",nullable = true,length = 5000)
    public String getTrainings(){return trainings;};
    /*@Column(name = "Contact", nullable = true, length = 50)
    public Contact getContact() {
        return contact;
    }*/

    public void setId(long id) {
        this.id = id;
    }
    public void setSkills(String skills) {
        this.skills = skills;
    }
    public void setExp(String exp) {
        this.exp = exp;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }
    public void setEdu(String edu) {
        this.edu = edu;
    }
    public void setTrainings(String trainings){this.trainings=trainings;};
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE", unique = true, nullable = false, length = 10)
    public void setDate(Date date) {
        this.date = date;
    }
    public void setUsersByFK(Users usersByFK) {
        this.usersByFK = usersByFK;
    }

}
