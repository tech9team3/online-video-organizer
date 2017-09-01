package rs.levi9.tech9.team3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.levi9.tech9.team3.domain.*;
import rs.levi9.tech9.team3.repository.CommentRepository;
import rs.levi9.tech9.team3.repository.UserRepository;
import rs.levi9.tech9.team3.repository.VideoRepository;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private VideoRepository videoRepository;
    private NotificationService notificationService;
    private UserRepository userRepository;
    private VideoService videoService;
    private ReportService reportService;


    @Autowired
    public CommentService(CommentRepository commentRepository,
                          VideoRepository videoRepository,
                          NotificationService notificationService,
                          VideoService videoService,
                          ReportService reportService,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
        this.videoService = videoService;
        this.reportService = reportService;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findOne(Long id) {

        return commentRepository.findOne(id);
    }

    public Comment save(Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        Video commentedVideo = comment.getVideo();
        commentedVideo.setNumberOfComments(this.getNumberOfCommentsForVideo(commentedVideo.getId()));
        videoService.save(commentedVideo);

        Notification notification = new Notification();
        User userToNotify = commentedVideo.getUser();

        notification.setStatus(true);
        notification.setComment(savedComment);
        notification.setCreationDate(savedComment.getCreationDate());
        notification.setUser(userToNotify);
        notificationService.save(notification);
        notificationService.sendNotification(userToNotify, savedComment);
        return savedComment;
    }

    public void delete(Long id) {

        Report foundReport = reportService.findByComment(id);
        if (foundReport != null) {
            System.out.println("Id reporta"+foundReport.getId());
            reportService.delete(foundReport.getId());
        }
        Notification notification = notificationService.findByComment(id);
        if (notification != null) {
            notificationService.delete(notification.getId());
        }

        commentRepository.delete(id);


    }

    public List<Comment> findAllCommentsForVideo(Long videoId) {
        Video foundVideo = videoRepository.findOne(videoId);
        List<Comment> listOfComments = commentRepository.findAllByVideoOrderByCreationDateDesc(foundVideo);
        return listOfComments;
    }

    public List<Comment> findAllCommentsForUser(Long userId) {
        User foundUser = userRepository.findOne(userId);
        List<Comment> listOfComments = commentRepository.findAllByUser(foundUser);
        return listOfComments;

    }

    public Long getNumberOfCommentsForVideo(Long videoId) {
        Video foundVideo = videoRepository.getOne(videoId);
        return commentRepository.countCommentByVideo(foundVideo);
    }
}