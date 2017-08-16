package rs.levi9.tech9.team3.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class VideoList extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -5009790045747517281L;

	@NotNull
	@ManyToOne
	@JoinColumn( nullable = false)
	private User user;

	@NotNull
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = true, columnDefinition = "tinyint(1) default 1")
	private Boolean visible;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public VideoList() {
	}

}
