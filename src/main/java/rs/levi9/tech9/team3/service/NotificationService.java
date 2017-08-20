package rs.levi9.tech9.team3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.Comment;
import rs.levi9.tech9.team3.domain.Notification;
import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.repository.CommentRepository;
import rs.levi9.tech9.team3.repository.NotificationRepository;
import rs.levi9.tech9.team3.repository.UserRepository;

@Service
public class NotificationService {

	private NotificationRepository notificationRepository;
	private JavaMailSender javaMailSender;
	private CommentRepository commentRepository;
	private UserRepository userRepository;
	

	@Autowired
	public NotificationService(NotificationRepository notificationRepository, JavaMailSender javaMailSender,CommentRepository commentRepository,UserRepository userRepository) {
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
		
	
	public List<Notification> findAllNotificationsByUser(Long userId){
		User foundUser = userRepository.findOne(userId);
		List<Notification> notificationList = notificationRepository.findByUser(foundUser);
		return notificationList;
	}
	
	public void sendRegistrationNotification(User user) throws MailException {
		// send email
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("organizetech9@gmail.com");
		mail.setSubject("Online video organize registration.");
		mail.setText("This is a email notification after you registerd to Online Video Organize website");

		javaMailSender.send(mail);
	}

	public void sendNotification(User user, Comment comment) {
		User commentAuthor = comment.getUser();
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("organizetech9@gmail.com");
		mail.setSubject("Comment notification.");
		mail.setText("This is a email notification after "+commentAuthor.getUsername()+" posted a comment: "+comment.getContent()+" on one of your video: "+comment.getVideo().getTitle());

		javaMailSender.send(mail);
	}

}
