package rs.levi9.tech9.team3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.levi9.tech9.team3.domain.*;
import rs.levi9.tech9.team3.repository.*;

import java.util.List;
import java.util.Set;

@Service
public class VideoService {

    private VideoRepository videoRepository;
    private CommentRepository commentRepository;
    private VideoListRepository videoListRepository;
    private RateRepository rateRepository;
    private UserRepository userRepository;
    private VideoTagService videoTagService;
    private NotificationService notificationService;
    private ReportService reportService;

    @Autowired
    public VideoService(VideoRepository videoRepository, CommentRepository commentRepository,
                        VideoListRepository videoListRepository, RateRepository rateRepository, UserRepository userRepository,
                        VideoTagService videoTagService, NotificationService notificationService, ReportService reportService) {

        this.videoRepository = videoRepository;
        this.commentRepository = commentRepository;
        this.videoListRepository = videoListRepository;
        this.rateRepository = rateRepository;
        this.userRepository = userRepository;
        this.videoTagService = videoTagService;
        this.notificationService = notificationService;
        this.reportService = reportService;
    }

    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    public Video findOne(Long id) {
        return videoRepository.findOne(id);
    }

    public Video save(Video video) {
        if (video.getNumberOfComments() == null) {
            video.setNumberOfComments(0l);
        }
        if (video.getAverageRate() == null) {
            video.setAverageRate(0.0);
        }
        Set<VideoTag> tagSet = video.getVideoTag();

        for (VideoTag videoTag : tagSet) {
            VideoTag foundVideoTag = videoTagService.findOneByName(videoTag.getName());
            if (foundVideoTag == null) {
                videoTag.setStatus(true);
                videoTagService.save(videoTag);
            } else {
                videoTag.setId(foundVideoTag.getId());
            }
        }
        return videoRepository.save(video);
    }

    public void delete(Long id) {
        Video foundVideo = videoRepository.findOne(id);

        List<Comment> commentList = commentRepository.findAllByVideoOrderByCreationDateDesc(foundVideo);
        for (Comment comment : commentList) {

            Notification notification = notificationService.findByComment(comment.getId());
            if (notification != null) {
                notificationService.delete(notification.getId());
            }
            Report report = reportService.findByComment(comment.getId());
            if (report != null) {
                reportService.delete(report.getId());
            }
            commentRepository.delete(comment.getId());
        }
        List<Rate> rateList = rateRepository.findAllByVideo(foundVideo);
        if (rateList != null) {
            for (Rate rate : rateList) {
                rateRepository.delete(rate.getId());
            }
        }

        videoRepository.delete(id);
    }

    public List<Video> findAllVideoByVideoList(Long videoListId) {
        VideoList foundVideoList = videoListRepository.findOne(videoListId);
        List<Video> listOfVideos = videoRepository.getAllByVideoList(foundVideoList);
        return listOfVideos;
    }

    public List<Video> findAllVideoByUser(Long userId) {
        User foundUser = userRepository.findOne(userId);
        List<Video> userVideos = videoRepository.getAllByUser(foundUser);
        return userVideos;
    }

    public List<Video> findAllVisible() {
        return videoRepository.findByVisibleIsTrue();
    }

    public List<Video> findAllVisibleForVideoList(Long videoListId) {
        VideoList foundVideoList = videoListRepository.findOne(videoListId);
        List<Video> listOfVideos = videoRepository.findAllByVideoListAndVisibleIsTrue(foundVideoList);
        return listOfVideos;
    }
}
