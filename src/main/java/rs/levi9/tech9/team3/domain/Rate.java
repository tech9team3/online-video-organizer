package rs.levi9.tech9.team3.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "rate")
public class Rate extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 2018902269409557939L;

	@Range(min = 0, max = 5)
	@Column(nullable = true)
	private Long mark;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Video video;
	
	@Column(nullable = false)
	private Date creationDate;

	@NotNull()
	@Column(nullable = false)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getMark() {
		return mark;
	}

	public void setMark(Long mark) {
		this.mark = mark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Rate() {

	}

}