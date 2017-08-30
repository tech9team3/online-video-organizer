package rs.levi9.tech9.team3.web.controller;

import java.util.Date;
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

import rs.levi9.tech9.team3.domain.Rate;
import rs.levi9.tech9.team3.service.RateService;

@RestController
@RequestMapping("/rate")
public class RateController {

	private RateService rateService;

	@Autowired
	public RateController(RateService rateService) {
		this.rateService = rateService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Rate> findAll() {
		return rateService.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity findOne(@PathVariable("id") Long id) {
		Rate rate = rateService.findOne(id);
		if (rate == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(rate, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Rate save(@Valid @RequestBody Rate rate) {		
		rate.setCreationDate(new Date());
		return rateService.save(rate);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		rateService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Rate put(@Valid @RequestBody Rate rate) {
		return rateService.save(rate);
	}

	@RequestMapping(path = "/search/ratesForVideo/{videoId}", method = RequestMethod.GET)
	public List<Rate> findAllRatesForVideoByVideoId(@PathVariable("videoId") Long videoId) {
		return rateService.findAllRatesForVideo(videoId);
	}
	
	@RequestMapping(path = "/search/mark/video/{videoId}/user/{username}", method = RequestMethod.GET)
	public Rate findRateByVideoAndUser(@PathVariable("videoId")long videoId, @PathVariable("username") String username){
		return rateService.findRateByVideoAndUser(videoId, username);
	}

}