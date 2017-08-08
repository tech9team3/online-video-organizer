package rs.levi9.tech9.team3.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Video extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 5858019068646342779L;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;
	
	@NotNull
	@Column(nullable = false)
	private String videoUrl;
	
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "videoId")	
	private Set<VideoTag> videoTags;
	
	@NotNull
	@Column(nullable = false)
	private Long videoListId;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<VideoTag> getVideoTags() {
		return videoTags;
	}

	public void setVideoTags(Set<VideoTag> videoTags) {
		this.videoTags = videoTags;
	}

	public Long getVideoListId() {
		return videoListId;
	}

	public void setVideoListId(Long videoListId) {
		this.videoListId = videoListId;
	}
}
