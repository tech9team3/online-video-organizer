package rs.levi9.tech9.team3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.Video;
import rs.levi9.tech9.team3.domain.VideoTag;
import rs.levi9.tech9.team3.repository.VideoRepository;
import rs.levi9.tech9.team3.repository.VideoTagRepository;

@Service
public class VideoTagService {
	private VideoTagRepository videoTagRepository;
	private VideoRepository videoRepository;

	@Autowired
	public VideoTagService(VideoTagRepository videoTagRepository,VideoRepository videoRepository) {
		this.videoTagRepository = videoTagRepository;
		this.videoRepository = videoRepository;
	}

	public List<VideoTag> findAll() {
		return videoTagRepository.findAll();
	}

	public VideoTag findOne(Long id) {
		return videoTagRepository.findOne(id);
	}

	public VideoTag save(VideoTag videoTag) {
		return videoTagRepository.save(videoTag);
	}

	public void delete(Long id) {
		videoTagRepository.delete(id);
	}
	
	public List<VideoTag> findAllTagsForVideo(Long videoId){
		Video foundVideo = videoRepository.findOne(videoId);
		List<VideoTag> videoTagList = videoTagRepository.findAllByVideo(foundVideo);
		return videoTagList;
	}
}
