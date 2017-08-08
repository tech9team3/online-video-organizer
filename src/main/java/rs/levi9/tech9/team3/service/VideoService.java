package rs.levi9.tech9.team3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.Video;
import rs.levi9.tech9.team3.repository.VideoRepository;

@Service
public class VideoService {
	private VideoRepository videoRepository;

	@Autowired
	public VideoService(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	public List<Video> findAll() {
		return videoRepository.findAll();
	}

	public Video findOne(Long id) {
		return videoRepository.findOne(id);
	}

	public Video save(Video video) {
		return videoRepository.save(video);
	}

	public void delete(Long id) {
		videoRepository.delete(id);
	}
}
