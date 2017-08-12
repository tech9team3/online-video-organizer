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

import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.service.CommentService;
import rs.levi9.tech9.team3.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private CommentService commentService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<User> findAll() {
		return userService.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity findOne(@PathVariable("id") Long id) {
		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public User save(@Valid @RequestBody User user) {

		return userService.save(user);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public User put(@Valid @RequestBody User user) {
		return userService.save(user);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		
		userService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}

}
