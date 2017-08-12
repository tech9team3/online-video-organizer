package rs.levi9.tech9.team3.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Video extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 5858019068646342779L;

	@NotNull
	@Column(nullable = false)
	private String videoUrl;

	@NotNull
	@Column(nullable = false)
	private String title;

	@NotNull
	@Column(nullable = false)
	private String description;

	@NotNull
	@ManyToOne 
	@JoinColumn( nullable = false)
	private VideoList videoList;

	@NotNull
	@ManyToOne
	@JoinColumn( nullable = false)
	private User user;

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public VideoList getVideoList() {
		return videoList;
	}

	public void setVideoList(VideoList videoList) {
		this.videoList = videoList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public Video() {
	}

}
