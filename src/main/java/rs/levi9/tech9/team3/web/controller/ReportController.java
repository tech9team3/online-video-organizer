package rs.levi9.tech9.team3.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import rs.levi9.tech9.team3.domain.Notification;
import rs.levi9.tech9.team3.domain.Report;
import rs.levi9.tech9.team3.service.NotificationService;
import rs.levi9.tech9.team3.service.ReportService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private ReportService reportService;
    private NotificationService notificationService;

    @Autowired
    public ReportController(ReportService reportService,NotificationService notificationService) {
        this.reportService = reportService;
        this.notificationService = notificationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Report> findAll() {
        return reportService.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable("id") Long id) {
        Report report = reportService.findOne(id);
        if (report == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(report, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Report save(@Valid @RequestBody Report report) {
        return reportService.save(report);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        reportService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Report put(@Valid @RequestBody Report report) {
    		report.setStatus(false);
        return reportService.save(report);
    }
    
    @RequestMapping(path = "/commentReport", method = RequestMethod.POST)
    public void reportCommentToAdmin(@Valid @RequestBody Report report){
        notificationService.sendReportToAdmin(report);
    }
    @RequestMapping(path = "/getNewReports", method = RequestMethod.GET)
    public List<Report> findNewReportsForUser(){
    		return reportService.findNewReportAuthor();
    }
    
//    @RequestMapping(path = "/getNewNotifications/{userId}", method = RequestMethod.GET)
//    public List<Notification> getListOfNewNotifications(@PathVariable("userId") Long userId){
//    	    return notificationService.findAllNewNotificationsRateAndComment(userId);
//    }
}
