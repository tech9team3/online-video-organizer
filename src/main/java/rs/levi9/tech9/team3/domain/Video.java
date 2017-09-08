package rs.levi9.tech9.team3.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Video extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5858019068646342779L;

    @NotNull
    @Column(nullable = false)
    private String videoUrl;

    @NotNull
    @Column(nullable = false)
    private String title;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
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

    @Column(nullable = true)
    private String videoPlayerUrl;

    @Column(nullable = true)
    private String videoImageUrl;


    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "video_tag_id"))
    private Set<VideoTag> videoTag;

    @Column(nullable = true)
    private Long numberOfComments;

    @Column(nullable = true)
    private Double averageRate;

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

    public String getVideoPlayerUrl() {
        return videoPlayerUrl;
    }

    public void setVideoPlayerUrl(String videoPlayerUrl) {
        this.videoPlayerUrl = videoPlayerUrl;
    }

    public String getVideoImageUrl() {
        return videoImageUrl;
    }

    public void setVideoImageUrl(String videoImageUrl) {
        this.videoImageUrl = videoImageUrl;
    }

    public Set<VideoTag> getVideoTag() {
        return videoTag;
    }

    public void setVideoTag(Set<VideoTag> videoTag) {
        this.videoTag = videoTag;
    }

    public Long getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(Long numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public Double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Double averageRate) {
        this.averageRate = averageRate;
    }

    public Video() {
    }


}
