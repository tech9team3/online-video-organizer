package rs.levi9.tech9.team3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.levi9.tech9.team3.domain.Comment;
import rs.levi9.tech9.team3.domain.Notification;
import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.domain.Video;
import rs.levi9.tech9.team3.repository.CommentRepository;
import rs.levi9.tech9.team3.repository.VideoRepository;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private VideoRepository videoRepository;
    private NotificationService notificationService;
    private UserService userService;

    @Autowired
    public CommentService(CommentRepository commentRepository, VideoRepository videoRepository, UserService
            userService, NotificationService notificationService) {
        this.commentRepository = commentRepository;
        this.videoRepository = videoRepository;
        this.userService = userService;
        this.notificationService = notificationService;
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
        Notification notification = new Notification();
//		System.out.println(commentedVideo.getId());
        User userToNotify = commentedVideo.getUser();
//		System.out.println(userToNotify.getFirstName());

        notificationService.sendNotification(userToNotify, savedComment);
        notification.setComment(savedComment);
        notification.setCreationDate(savedComment.getCreationDate());
        notification.setUser(userToNotify);
        notificationService.save(notification);
        return savedComment;
    }

    public void delete(Long id) {

        Notification notification = notificationService.findByComment(id);
        if (notification!=null) {
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
        User foundUser = userService.findOne(userId);
        List<Comment> listOfComments = commentRepository.findAllByUser(foundUser);
        return listOfComments;

    }
}