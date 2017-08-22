package rs.levi9.tech9.team3.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "notification")
public class Notification extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 4620567645342653211L;

	@NotNull
	@OneToOne
	@JoinColumn(nullable = false)
	private Comment comment;

	@NotNull
	@OneToOne
	@JoinColumn(nullable = false)
	private Rate rate;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;// primaoc notifikacije

	public Comment getComment() {
		return comment;
	}

	@NotNull()
	@Column(nullable = false)
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

	public Notification() {
	}

}
