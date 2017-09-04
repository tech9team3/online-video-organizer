package rs.levi9.tech9.team3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.levi9.tech9.team3.domain.Comment;
import rs.levi9.tech9.team3.domain.Notification;
import rs.levi9.tech9.team3.domain.Report;
import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.repository.CommentRepository;
import rs.levi9.tech9.team3.repository.ReportRepository;
import rs.levi9.tech9.team3.repository.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class ReportService {
    private ReportRepository reportRepository;
    private UserRepository userRepository;
    private NotificationService notificationService;
    private CommentRepository commentRepository;


    @Autowired
    public ReportService(ReportRepository reportRepository,
                         UserRepository userRepository,
                         NotificationService notificationService,
                         CommentRepository commentRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
        this.commentRepository= commentRepository;
    }

    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    public Report findOne(Long id) {
        return reportRepository.findOne(id);
    }

    public Report save(Report report) {
    		if(report.getId()!=null){
    			report.setStatus(true);
    		}
    	
    	Report savedReport = reportRepository.save(report);

        Notification notification = new Notification();
        notification.setReport(savedReport);
        notificationService.save(notification);
        notificationService.sendReportToAdmin(report);
        return savedReport;
    }

    public void delete(Long id) {
        Notification foundNotification = notificationService.findOneByReport(id);
        notificationService.delete(foundNotification.getId());
        reportRepository.delete(id);
    }

    public Report findByComment(Long commentId){
        Comment foundComment = commentRepository.findOne(commentId);
        return  reportRepository.findByReportedComment(foundComment);
    }

    public List<Report> findByReportAuthor(Long userId){
        User foundUser = userRepository.findOne(userId);
        return  reportRepository.findByReportAuthor(foundUser);
    }

}
