package rs.levi9.tech9.team3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.domain.VideoList;
import rs.levi9.tech9.team3.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private VideoListService videoListService;

	@Autowired
	public UserService(UserRepository userRepository,VideoListService videoListService) {
		this.userRepository = userRepository;
		this.videoListService = videoListService;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	public User save(User user) {
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
	
}
