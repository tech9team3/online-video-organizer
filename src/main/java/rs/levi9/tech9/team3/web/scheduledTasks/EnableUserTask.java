package rs.levi9.tech9.team3.web.scheduledTasks;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.service.NotificationService;
import rs.levi9.tech9.team3.service.UserService;

@Component
public class EnableUserTask {

    private UserService userService;
    private NotificationService notificationService;

    @Autowired
    public EnableUserTask(UserService userService,
                          NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 1*60*1000)
    public void checkBanList() throws MailException, MessagingException {

        List<User> listBanUsers=userService.listOfBanUsers();
        if(listBanUsers.isEmpty()){
//            System.out.println("Niko nije dobio ban");
        }else {

            for (User user : listBanUsers) {
//                System.out.print("Korisnik ~"+user.getUsername()+" ima ban");
//                System.out.println(" koji traje do "+user.getBanExpirationDate().toString());
                long banMiliSeconds = user.getBanExpirationDate().getTime();
                long currentTimeMiliSeconds = System.currentTimeMillis();

                if (banMiliSeconds < currentTimeMiliSeconds) {

                    user.setStatus(true);
                    user.setBanExpirationDate(null);
//                    System.out.println("Korisnik ~:"+user.getUsername()+" vise nije banovan");
                    userService.save(user);
                    if(user.getStatus()){
                        notificationService.sendAccountEnabledNotification(user);
                    }
                }
            }
        }
    }

}
