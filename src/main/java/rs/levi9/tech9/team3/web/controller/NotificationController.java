package rs.levi9.tech9.team3.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.levi9.tech9.team3.domain.Notification;
import rs.levi9.tech9.team3.domain.Report;
import rs.levi9.tech9.team3.service.NotificationService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {


    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Notification> findAll() {
        return notificationService.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable("id") Long id) {
        Notification notification = notificationService.findOne(id);
        if (notification == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(notification, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Notification save(@Valid @RequestBody Notification notification) {
        notification.setCreationDate(new Date());
        return notificationService.save(notification);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        notificationService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Notification put(@Valid @RequestBody Notification notification) {
        return notificationService.save(notification);
    }
    
    @RequestMapping(path = "/forUser/{userId}", method = RequestMethod.GET)		
    	public List<Notification> getListOfNotificationForUser(@PathVariable("userId") Long userId){		
    		return notificationService.findAllNotificationsByUser(userId);		
    	}
}
