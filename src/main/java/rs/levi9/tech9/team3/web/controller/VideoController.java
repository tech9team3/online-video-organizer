package rs.levi9.tech9.team3.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.levi9.tech9.team3.domain.Video;
import rs.levi9.tech9.team3.service.VideoService;

@RestController
@RequestMapping("/videos")
public class VideoController {
	private VideoService videoService;

	@Autowired
	public VideoController(VideoService videoService) {
		this.videoService = videoService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Video> findAll() {
		return videoService.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity findOne(@PathVariable("id") Long id) {
		Video video = videoService.findOne(id);
		if (video == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(video, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Video save(@Valid @RequestBody Video video) {
		return videoService.save(video);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Video put(@Valid @RequestBody Video video) {
		return videoService.save(video);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		videoService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(path = "/search/videos/byVideoList/{videoListId}", method = RequestMethod.GET)
	public List<Video> findAllVideosForVideoList(@PathVariable("videoListId") Long videoListId) {
		return videoService.findAllVideoByVideoList(videoListId);
	}
	
	@RequestMapping(path = "/search/videos/byUser/{userId}", method = RequestMethod.GET)
	public List<Video> findAllVideosForUser(@PathVariable("userId") Long userId){
		return videoService.findAllVideoByUser(userId);
	}
	@RequestMapping(path = "/search/visible", method = RequestMethod.GET)
	public List<Video> findAllVideosThatArePublic(){
		return videoService.findAllVisible();
	}
	
}
