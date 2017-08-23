package rs.levi9.tech9.team3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.levi9.tech9.team3.domain.*;
import rs.levi9.tech9.team3.repository.CommentRepository;
import rs.levi9.tech9.team3.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private VideoListService videoListService;
    private NotificationService notificationService;
    private RateService rateService;
    private CommentRepository commentRepository;
    private VideoService videoService;
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository,
                       VideoListService videoListService,
                       NotificationService notificationService,
                       RateService rateService,
                       CommentRepository commentRepository,
                       VideoService videoService) {

        this.userRepository = userRepository;
        this.videoListService = videoListService;
        this.notificationService = notificationService;
        this.rateService = rateService;
        this.commentRepository = commentRepository;
        this.videoService = videoService;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public User save(User user) {

        if (userRepository.findByEmail(user.getEmail()) == null
                && userRepository.findByUsername(user.getUsername()) == null) {
            try {
                user.setStatus(false);
                userRepository.save(user);
                notificationService.sendRegistrationNotification(user);

            } catch (Exception e) {
                logger.info("Sending email error : " + e.getMessage());
            }
        }
        return userRepository.save(user);
    }


    public void delete(Long id) {
        User foundUser = userRepository.findOne(id);
        List<Rate> foundRateList = rateService.findAllRatesByUser(foundUser.getId());
        for (Rate rate : foundRateList) {
            rateService.delete(rate.getId());
        }

        List<Comment> foundCommentList = commentRepository.findAllByUser(foundUser);
        for (Comment comment : foundCommentList) {
            commentRepository.delete(comment.getId());
        }
        List<Notification> foundNotificationList = notificationService.findAllNotificationsByUser(foundUser.getId());
        for (Notification notification : foundNotificationList) {
            notificationService.delete(notification.getId());
        }

        List<Video> foundVideoList = videoService.findAllVideoByUser(foundUser.getId());
        for (Video video : foundVideoList) {
            videoService.delete(video.getId());
        }
        List<VideoList> foundVideoLists = videoListService.findAllVideoListsByUser(foundUser.getId());
        for (VideoList videoList : foundVideoLists) {
            videoListService.delete(videoList.getId());
        }

        userRepository.delete(id);
    }

    public User findOneByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
