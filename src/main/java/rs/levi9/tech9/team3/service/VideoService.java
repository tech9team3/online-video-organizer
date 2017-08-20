package rs.levi9.tech9.team3.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Video newVideo = new Video();
		String url = video.getVideoUrl();
		String providerName;
		newVideo = video;

		if (url.contains("youtube")) {
			// System.out.println("usao je u uslov za yt");

			String regExp = "/.*(?:youtu.be\\/|v\\/|u/\\w/|embed\\/|watch\\?.*&?v=)";
			Pattern compiledPattern = Pattern.compile(regExp);
			Matcher matcher = compiledPattern.matcher(url);

			if (matcher.find()) {
				// System.out.println("nasao je id");
				int start = matcher.end();
				System.out.println("videoId je : " + url.substring(start, start + 11));
				providerName = "youtube";
				newVideo.setProviderName(providerName);
				newVideo.setVideoUrlId(url.substring(start, start + 11));
			}

		} else if (url.contains("vimeo")) {
			// System.out.println("usao je u vimeo uslov");
			String regExp = "^.*(vimeo\\.com\\/)((channels\\/[A-z]+\\/)|(groups\\/[A-z]+\\/videos\\/))?([0-9]+)";
			Pattern compiledPattern = Pattern.compile(regExp);
			Matcher matcher = compiledPattern.matcher(url);
			if (matcher.find()) {
				String match = matcher.group();
				String idGroop = match.substring(match.lastIndexOf("/"));
				// System.out.println("videoId je : "+idGroop.substring(1));
				providerName = "vimeo";
				newVideo.setProviderName(providerName);
				newVideo.setVideoUrlId(idGroop.substring(1));

			}

		} else if (url.contains("dailymotion")) {

			// System.out.println("usao je u uslov za dailymotion.");

			String regExp = "/video/([^_]+)/?";
			Pattern compiledPattern = Pattern.compile(regExp);
			Matcher matcher = compiledPattern.matcher(url);
			if (matcher.find()) {
				String match = matcher.group();
				// System.out.println("videoId je : " + match.substring(match.lastIndexOf("/") +
				// 1));

				providerName = "dailymotion";
				newVideo.setProviderName(providerName);
				newVideo.setVideoUrlId(match.substring(match.lastIndexOf("/") + 1));

			}
		}

		return videoRepository.save(newVideo);
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
	
	public List<Video> findAllVisible(){
		return videoRepository.findByVisibleIsTrue();
	}
}
