package rs.levi9.tech9.team3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.VideoList;
import rs.levi9.tech9.team3.repository.VideoListRepository;

@Service
public class VideoListService {
	private VideoListRepository videoListRepository;

	@Autowired
	public VideoListService(VideoListRepository videoListRepository) {
		this.videoListRepository = videoListRepository;
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
		videoListRepository.delete(id);
	}
}
