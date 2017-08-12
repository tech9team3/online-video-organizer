package rs.levi9.tech9.team3.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;



@Entity
public class VideoTag extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -1101550298406481562L;

	@NotNull
	@Column(nullable = false)
	private String name;

	@NotNull
	@ManyToOne 
	@JoinColumn( nullable = false)
	private Video video;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public VideoTag() {
	}
	
	

}
