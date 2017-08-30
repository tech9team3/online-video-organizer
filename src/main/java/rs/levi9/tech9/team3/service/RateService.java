package rs.levi9.tech9.team3.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.Notification;
import rs.levi9.tech9.team3.domain.Rate;
import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.domain.Video;
import rs.levi9.tech9.team3.repository.RateRepository;
import rs.levi9.tech9.team3.repository.UserRepository;
import rs.levi9.tech9.team3.repository.VideoRepository;

@Service
public class RateService
{
    private RateRepository rateRepository;
    private VideoRepository videoRepository;
    private UserRepository userRepository;
    private NotificationService notificationService;

    @Autowired
	public RateService(RateRepository rateRepository,
			VideoRepository videoRepository,
			UserRepository userRepository,
			NotificationService notificationService) {
		this.rateRepository = rateRepository;
		this.videoRepository = videoRepository;
		this.userRepository = userRepository;
		this.notificationService = notificationService;
	}
    
	public List<Rate> findAll() {
		return rateRepository.findAll();
	}

	public Rate findOne(Long id) {
		return rateRepository.findOne(id);
	}

	public Rate save(Rate rate) {
		Rate savedRate = rateRepository.save(rate);
		Video ratedVideo = rate.getVideo();
		Notification notification = new Notification();
		User userToNotify = ratedVideo.getUser();
		
		notificationService.sendNotification(userToNotify, savedRate);
		notification.setRate(savedRate);
		notification.setCreationDate(rate.getCreationDate());
		notification.setUser(userToNotify);
		notificationService.save(notification);
		return savedRate;
	}

	public void delete(Long id) {
		rateRepository.delete(id);
	}
    
	public List<Rate> findAllRatesForVideo(Long videoId){
		Video foundVideo = videoRepository.findOne(videoId);
		List<Rate> videoRateLists = rateRepository.findAllByVideo(foundVideo);
		return videoRateLists;
	}
	public List<Rate> findAllRatesByUser(Long userId){
		User foundUser = userRepository.findOne(userId);
		List<Rate> listOfRates = rateRepository.findAllByUser(foundUser);
		return listOfRates;
	}
	
	public Rate findRateByVideoAndUser(long videoId, String username){
		User foundUser = userRepository.findByUsername(username);
		Video foundVideo = videoRepository.findOne(videoId);
		return rateRepository.findByVideoAndUser(foundVideo, foundUser);
	}
}