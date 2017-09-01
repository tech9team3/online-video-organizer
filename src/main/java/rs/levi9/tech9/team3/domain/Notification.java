package rs.levi9.tech9.team3.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4620567645342653211L;

    @OneToOne
    @JoinColumn(nullable = true)
    private Comment comment;

    @OneToOne
    @JoinColumn(nullable = true)
    private Rate rate;

    @OneToOne
    @JoinColumn(nullable = true)
    private Report report;

    @ManyToOne
    @JoinColumn(nullable = true)
    private User user;// primaoc notifikacije


    public Comment getComment() {
        return comment;
    }

    @Column(nullable = true)
    private Date creationDate;

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Notification() {
    }

}
