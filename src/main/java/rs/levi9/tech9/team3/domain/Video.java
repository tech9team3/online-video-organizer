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

	@ManyToOne
	@JoinColumn(nullable = true)
	private VideoList videoList;

	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	@Column(nullable = true)
	private String providerName;

	@Column(nullable = true)
	private String videoUrlId;

	@Column(nullable = true, columnDefinition = "tinyint(1) default 1")
	private Boolean visible;

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

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getVideoUrlId() {
		return videoUrlId;
	}

	public void setVideoUrlId(String videoUrlId) {
		this.videoUrlId = videoUrlId;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Video() {
	}

}
