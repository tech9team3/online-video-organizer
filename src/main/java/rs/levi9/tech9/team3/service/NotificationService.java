package rs.levi9.tech9.team3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import rs.levi9.tech9.team3.domain.Comment;
import rs.levi9.tech9.team3.domain.Notification;
import rs.levi9.tech9.team3.domain.Rate;
import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.repository.CommentRepository;
import rs.levi9.tech9.team3.repository.NotificationRepository;
import rs.levi9.tech9.team3.repository.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;
    private JavaMailSender javaMailSender;
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, JavaMailSender javaMailSender,
                               CommentRepository commentRepository, UserRepository userRepository, SimpMessagingTemplate simpMessagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.javaMailSender = javaMailSender;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Notification findOne(Long id) {
        return notificationRepository.findOne(id);
    }

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void delete(Long id) {
        notificationRepository.delete(id);
    }

    public Notification findByComment(Long commentId) {
        Comment foundComment = commentRepository.findOne(commentId);
        return notificationRepository.findByComment(foundComment);
    }

    public List<Notification> findAllNotificationsByUser(Long userId) {
        User foundUser = userRepository.findOne(userId);
        List<Notification> notificationList = notificationRepository.findByUserOrderByCreationDateDesc(foundUser);
        return notificationList;
    }

    public void sendRegistrationNotification(User user) throws MailException, MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        String text = "Thank you for registration. In order to use your account, you'll need to activate it. Just click " +
                "activate to confirm.<br><br><a href='http://localhost:8080/#/activate/"
                + user.getId() + "'>Activate your account " + user.getUsername() + "</a>";
        System.out.println(user.getId());
        helper = new MimeMessageHelper(message, true);
        helper.setFrom("organizetech9@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Registration email");
        helper.setText(text, true);
        javaMailSender.send(message);
    }

    public void sendNotification(User user, Comment comment) {
        User commentAuthor = comment.getUser();

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("organizetech9@gmail.com");
        mail.setSubject("Comment notification.");
        mail.setText("This is a email notification after " + commentAuthor.getUsername() + " posted a comment: "
                + comment.getContent() + " on one of your video: " + comment.getVideo().getTitle());

        javaMailSender.send(mail);
    }

    public void sendNotification(User user, Rate rate) {
        User ratetAuthor = rate.getUser();

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("organizetech9@gmail.com");
        mail.setSubject("Comment notification.");
        mail.setText("This is a email notification after" + ratetAuthor.getUsername() + " rated your video :"
                + rate.getVideo().getTitle());
        javaMailSender.send(mail);
    }

    public void sendPermanentBanNotification(User user){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("organizetech9@gmail.com");
        mail.setSubject("Ban notification.");
        mail.setText("Account with username: " +user.getUsername()+ " is permanently disabled.");
        javaMailSender.send(mail);
    }

    public void sendTemporarilyBanNotification(User user){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("organizetech9@gmail.com");
        mail.setSubject("Ban notification.");
        mail.setText("Account with username: " +user.getUsername()+ " is temporarily disabled. Untill :"+user.getBanExpirationDate().toString());
        javaMailSender.send(mail);
    }

    public void sendAccountEnabledNotification(User user){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("organizetech9@gmail.com");
        mail.setSubject("Ban notification.");
        mail.setText("Account with username: " +user.getUsername()+ " is enabled. You can use it again...");
        javaMailSender.send(mail);
    }
}
