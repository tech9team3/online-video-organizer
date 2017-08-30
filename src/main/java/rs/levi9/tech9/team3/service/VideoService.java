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

    @Autowired
    public VideoService(VideoRepository videoRepository,
                        CommentRepository commentRepository,
                        VideoListRepository videoListRepository,
                        RateRepository rateRepository,
                        UserRepository userRepository,
                        VideoTagService videoTagService
    ) {

        this.videoRepository = videoRepository;
        this.commentRepository = commentRepository;
        this.videoListRepository = videoListRepository;
        this.rateRepository = rateRepository;
        this.userRepository = userRepository;
        this.videoTagService = videoTagService;
    }

    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    public Video findOne(Long id) {
        return videoRepository.findOne(id);
    }

    public Video save(Video video) {

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
            commentRepository.delete(comment.getId());
        }
        List<Rate> rateList = rateRepository.findAllByVideo(foundVideo);
        for (Rate rate : rateList) {
            rateRepository.delete(rate.getId());
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
