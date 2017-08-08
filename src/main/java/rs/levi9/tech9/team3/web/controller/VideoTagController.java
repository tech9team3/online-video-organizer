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

import rs.levi9.tech9.team3.domain.VideoTag;
import rs.levi9.tech9.team3.service.VideoTagService;

@RestController
@RequestMapping("/videoTags")
public class VideoTagController {
	private VideoTagService videoTagService;

	@Autowired
	public VideoTagController(VideoTagService videoTagService) {
		this.videoTagService = videoTagService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<VideoTag> findAll() {
		return videoTagService.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity findOne(@PathVariable("id") Long id) {
		VideoTag videoTag = videoTagService.findOne(id);
		if (videoTag == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(videoTag, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public VideoTag save(@Valid @RequestBody VideoTag videoTag)  {	
		return videoTagService.save(videoTag);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public VideoTag put(@Valid @RequestBody VideoTag videoTag) {
		return videoTagService.save(videoTag);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		videoTagService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
