package rs.levi9.tech9.team3.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.Comment;
import rs.levi9.tech9.team3.domain.Rate;
import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.domain.Video;
import rs.levi9.tech9.team3.domain.VideoList;
import rs.levi9.tech9.team3.domain.VideoTag;
import rs.levi9.tech9.team3.repository.CommentRepository;
import rs.levi9.tech9.team3.repository.RateRepository;
import rs.levi9.tech9.team3.repository.UserRepository;
import rs.levi9.tech9.team3.repository.VideoListRepository;
import rs.levi9.tech9.team3.repository.VideoRepository;
import rs.levi9.tech9.team3.repository.VideoTagRepository;

@Service
public class VideoService {

	private VideoRepository videoRepository;
	private VideoTagRepository videoTagRepository;
	private CommentRepository commentRepository;
	private VideoListRepository videoListRepository;
	private RateRepository rateRepository;
	private UserRepository userRepository;

	@Autowired
	public VideoService(VideoRepository videoRepository, VideoTagRepository videoTagRepository,
			CommentRepository commentRepository, VideoListRepository videoListRepository, RateRepository rateRepository,
			UserRepository userRepository) {
		this.videoRepository = videoRepository;
		this.videoTagRepository = videoTagRepository;
		this.commentRepository = commentRepository;
		this.videoListRepository = videoListRepository;
		this.rateRepository = rateRepository;
		this.userRepository = userRepository;
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
		Video foundVideo = videoRepository.findOne(id);
		List<Comment> commentList = commentRepository.findAllByVideo(foundVideo);
		for (Comment comment : commentList) {
			commentRepository.delete(comment.getId());
		}
		List<VideoTag> videoTagList = videoTagRepository.findAllByVideo(foundVideo);
		for (VideoTag videoTag : videoTagList) {
			videoTagRepository.delete(videoTag.getId());
		}
		List<Rate> rateList = rateRepository.findAllByVideo(foundVideo);
		for (Rate rate : rateList) {
			rateRepository.delete(rate.getId());
		}
		videoRepository.delete(id);
	}

	public List<Video> findAllVideoByVideoList(Long videoListId) {
		VideoList foundVideoList = videoListRepository.findOne(videoListId);
		List<Video> listOfVideos = videoRepository.getAllByVideoList(foundVideoList);
		return listOfVideos;
	}

	public List<Video> findAllVideoByUser(Long userId) {
		User foundUser = userRepository.findOne(userId);
		List<Video> userVideos = videoRepository.getAllByUser(foundUser);
		return userVideos;
	}

	public List<Video> findAllVisible() {
		return videoRepository.findByVisibleIsTrue();
	}
	
	public List<Video> findAllVisibleForVideoList(Long videoListId){
		VideoList foundVideoList = videoListRepository.findOne(videoListId);
		List<Video> listOfVideos = videoRepository.findAllByVideoListAndVisibleIsTrue(foundVideoList);
		 return listOfVideos;
	}
}
