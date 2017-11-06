package rs.levi9.tech9.team3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import rs.levi9.tech9.team3.domain.*;
import rs.levi9.tech9.team3.repository.CommentRepository;
import rs.levi9.tech9.team3.repository.UserRepository;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private VideoListService videoListService;
    private NotificationService notificationService;
    private RateService rateService;
    private CommentRepository commentRepository;
    private VideoService videoService;
    private ReportService reportService;
    private CommentService commentService;


    @Autowired
    public UserService(UserRepository userRepository,
                       VideoListService videoListService,
                       NotificationService notificationService,
                       RateService rateService,
                       CommentRepository commentRepository,
                       VideoService videoService,
                       ReportService reportService,
                       CommentService commentService) {

        this.userRepository = userRepository;
        this.videoListService = videoListService;
        this.notificationService = notificationService;
        this.rateService = rateService;
        this.commentRepository = commentRepository;
        this.videoService = videoService;
        this.reportService = reportService;
        this.commentService = commentService;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public User save(User user) throws MailException, MessagingException  {
    			if(user.getId()==null){
                user.setStatus(false);
                userRepository.save(user);
                notificationService.sendRegistrationNotification(user);
    			}
                
        return userRepository.save(user);
    }


    public void delete(Long id) {

    	
    	User foundUser = userRepository.findOne(id);

    	 List<Rate> foundRateList = rateService.findAllRatesByUser(foundUser.getId());
         for (Rate rate : foundRateList) {
             rateService.delete(rate.getId());
         }
         List<Report> foundReportList = reportService.findByReportAuthor(id);
         for (Report report: foundReportList) {
             reportService.delete(report.getId());
         }
        
         List<Comment> foundCommentList = commentService.findAllCommentsForUser(foundUser.getId());
         for (Comment comment : foundCommentList) {
             commentService.delete(comment.getId());
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

    public  List<User> listOfBanUsers(){
        Date startDate = new Date(System.currentTimeMillis() - 3600 * 1000);
        Date endDate = new Date(System.currentTimeMillis() + 3600 * 1000);
//        System.out.println("Start date : "+startDate.toString());
//        System.out.println("End date : "+endDate.toString());

        return userRepository.findAllByBanExpirationDateIsNotNullAndBanExpirationDateAfterAndBanExpirationDateBefore(startDate,endDate);
    }
    public void setBanToUser (String userName, Date banExpDate) throws MailException, MessagingException {
        User foundUser = this.findOneByUsername(userName);
        foundUser.setBanExpirationDate(banExpDate);
        foundUser.setStatus(false);
        this.save(foundUser);
        if(foundUser.getStatus()!=true){
            notificationService.sendTemporarilyBanNotification(foundUser);
        }
    }
}
