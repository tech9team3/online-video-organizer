package rs.levi9.tech9.team3.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.domain.VideoList;
import rs.levi9.tech9.team3.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private VideoListService videoListService;
	private NotificationService notificationService;
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	public UserService(UserRepository userRepository, VideoListService videoListService,
			NotificationService notificationService) {
		this.userRepository = userRepository;
		this.videoListService = videoListService;
		this.notificationService = notificationService;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	public User save(User user) {
		if(user.getId()==null) {
			notificationService.sendRegistrationNotification(user);
		}
		return userRepository.save(user);
	}

	public void delete(Long id) {
		User foundUser = userRepository.findOne(id);
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
}
