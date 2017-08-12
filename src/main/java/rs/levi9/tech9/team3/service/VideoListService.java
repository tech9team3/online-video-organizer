package rs.levi9.tech9.team3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.domain.Video;
import rs.levi9.tech9.team3.domain.VideoList;
import rs.levi9.tech9.team3.repository.UserRepository;
import rs.levi9.tech9.team3.repository.VideoListRepository;
import rs.levi9.tech9.team3.repository.VideoRepository;

@Service
public class VideoListService {
	private VideoListRepository videoListRepository;
	private VideoRepository videoRepository;
	private VideoService videoService;
	private UserRepository userRepository;

	@Autowired
	public VideoListService(VideoListRepository videoListRepository, VideoRepository videoRepository,UserRepository userRepository,
			VideoService videoService) {
		this.videoListRepository = videoListRepository;
		this.videoRepository = videoRepository;
		this.videoService = videoService;
		this.userRepository = userRepository;
	}

	public List<VideoList> findAll() {
		return videoListRepository.findAll();
	}

	public VideoList findOne(Long id) {
		return videoListRepository.findOne(id);
	}

	public VideoList save(VideoList videoList) {
		return videoListRepository.save(videoList);
	}

	public void delete(Long id) {
		VideoList foundVideoList = videoListRepository.findOne(id);
		List<Video> videoList = videoRepository.getAllByVideoList(foundVideoList);

		for (Video video : videoList) {
			videoService.delete(video.getId());
		}
		videoListRepository.delete(id);
	}
	
	public List<VideoList> findAllVideoListsByUser(Long userId){
		User foundUser = userRepository.findOne(userId);
		return videoListRepository.findAllByUser(foundUser);
	}
}
