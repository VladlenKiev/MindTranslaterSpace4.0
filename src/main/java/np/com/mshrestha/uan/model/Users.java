package np.com.mshrestha.uan.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 12.09.2016.
 */

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
        @NamedQuery(name = "Users.findAll", query = "SELECT us FROM Users us"),
        @NamedQuery(name = "Users.findByName", query = "SELECT us FROM Users us where name=:idReq")
})
public class Users {
    private long id;
    private String name;
    private String email;
    private List<CV> cves = new ArrayList<>();

    public Users(){    }
    public Users(String name, String email){
        this.name = name;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", unique = true, nullable = false)
    public long getId() {
        return id;
    }
    @OneToMany(mappedBy = "usersByFK", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //public List<CV> getCves() {        return Collections.unmodifiableList(cves);    }
    public List<CV> getCves() {        return cves;    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCves(List<CV> cves) {
        this.cves = cves;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void addCV(CV cv){
        cv.setUsersByFK(this);
        cves.add(cv);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cves=" + cves +
                '}';
    }
}
