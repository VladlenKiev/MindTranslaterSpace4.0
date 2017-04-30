package np.com.mshrestha.uan.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "associate")
public class Associate {

  private Long id;
  private Integer phID;
  private String associate;
  private String sessionID;
  private Date date = new Date();

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }
  @Column(nullable = true)
  public Integer getPhID() {
    return phID;
  }
  @Column(nullable = true)
  public String getAssociate() {
    return associate;
  }
  @Column(nullable = false)
   public Date getDate() {
    return this.date;
  }
  @Column
  public String getSessionID() {
    return sessionID;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setAssociate(String associate) {
    this.associate = associate;
  }

  public void setPhID(Integer phID) {
    this.phID = phID;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "DATE", unique = true, nullable = false, length = 10)
  public void setDate(Date date) {
    this.date = date;
  }

  public void setSessionID(String sessionID) {
    this.sessionID = sessionID;
  }
}
