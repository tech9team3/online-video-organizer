package rs.levi9.tech9.team3.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comment")
public class Comment extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -8302558199993563066L;

	@Column(nullable = true)
	private String content;

	@NotNull()
	@Column(nullable = false)
	private Date creationDate;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "user_id", nullable = false)
	private User author;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Comment() {
	}

}
