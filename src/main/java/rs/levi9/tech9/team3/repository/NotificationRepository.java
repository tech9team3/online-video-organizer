package rs.levi9.tech9.team3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.levi9.tech9.team3.domain.*;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
public Notification findByComment(Comment comment);
public List<Notification> findByUserOrderByCreationDateDesc(User user);
public Notification findByReport(Report report);
}
