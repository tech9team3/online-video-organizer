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

import rs.levi9.tech9.team3.domain.VideoList;
import rs.levi9.tech9.team3.service.VideoListService;

@RestController
@RequestMapping("/videoLists")
public class VideoListController {
	private VideoListService videoListService;

	@Autowired
	public VideoListController(VideoListService videoListService) {
		this.videoListService = videoListService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<VideoList> findAll() {
		return videoListService.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity findOne(@PathVariable("id") Long id) {
		VideoList videoList = videoListService.findOne(id);
		if (videoList == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(videoList, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public VideoList save(@Valid @RequestBody VideoList videoList)  {	
		return videoListService.save(videoList);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public VideoList put(@Valid @RequestBody VideoList videoList) {
		return videoListService.save(videoList);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		videoListService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(path = "/search/userId/{userId}", method = RequestMethod.GET)
	public List<VideoList> findAllVideoListsForUserByUserId(@PathVariable("userId") Long userId){
		return videoListService.findAllVideoListsByUser(userId);
	}
}
