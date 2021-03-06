package rs.levi9.tech9.team3.web.controller;

import java.net.URI;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.messaging.simp.user.UserRegistryMessageHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.repository.UserRepository;
import rs.levi9.tech9.team3.service.CommentService;
import rs.levi9.tech9.team3.service.UserService;
import rs.levi9.tech9.team3.web.validation.exceptions.EmailAlreadyExistsException;
import rs.levi9.tech9.team3.web.validation.exceptions.UsernameAlreadyExistsException;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private CommentService commentService;

	@Autowired
	private RestOperations restTemplate;
	
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
	public User save(@Valid @RequestBody User user) throws UsernameAlreadyExistsException, EmailAlreadyExistsException, MailException, MessagingException {
		user.setRegistrationDate(new Date());
		
		User fondUser = userService.findOneByUsername(user.getUsername());
		if(fondUser!=null){
			throw new UsernameAlreadyExistsException("Username already taken, choose another one!");
		}
		User foundEmailUser =userService.findOneByEmail(user.getEmail());
		if(foundEmailUser!=null){
			throw new EmailAlreadyExistsException("User with this email already exists!");
		}
		
		
		 
		return userService.save(user);
	}

	@RequestMapping(path = "/activateUser/{userId}", method = RequestMethod.GET)
	public String enableUser( @PathVariable Long userId) throws MailException, MessagingException{
		User foundUser = userService.findOne(userId);
		
		foundUser.setStatus(true);
		return userService.save(foundUser).getUsername();
		
	}
	@RequestMapping(method = RequestMethod.PUT)
	public User put(@Valid @RequestBody User user) throws MailException, MessagingException {
		return userService.save(user);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		
		userService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}


	@RequestMapping(path = "searchByName/{username}", method = RequestMethod.GET)
	public User findOneByUsername(@PathVariable("username") String username) {
		return userService.findOneByUsername(username);
	}

	@RequestMapping(path = "searchByEmail/{email}", method = RequestMethod.GET)
	public User findOneByEmail(@PathVariable("email") String email) {
		return userService.findOneByEmail(email);
	}
	
    @RequestMapping("/user")
    public Map<String, Object> user(Authentication user) {
      Map<String, Object> map = new LinkedHashMap<String, Object>();
      map.put("id", (userService.findOneByUsername(user.getName())).getId());
      map.put("username", user.getName());
      map.put("roles", AuthorityUtils.authorityListToSet((user).getAuthorities()));
      return map;
    }
    
    @RequestMapping(path ="/captcha", method = RequestMethod.POST)
    public String getSomethingSomething(@RequestBody Map<String, String> request){
    	String response = request.get("g-recaptcha-response");
    	String secret = "6LceCy0UAAAAAIOUKGpGoM2tlct9Et_JdO9xLjA9";
    	URI verifyUri = URI.create(String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s", secret, response));
    	String googleResponse = restTemplate.getForObject(verifyUri, String.class);
    	System.out.println(googleResponse);
    	return googleResponse;
    }

	@RequestMapping(path ="/admin/banUsers", method = RequestMethod.GET)
	public List<User> getListOfBanedUsers2Hours(){
    	return  userService.listOfBanUsers();
	}

	@RequestMapping(path = "/admin/banUser/{username}/forTime/{time}", method = RequestMethod.GET)
	public void banUser(@PathVariable("username") String username,
						@PathVariable("time")Long time) throws MailException, MessagingException{
			Long newDate = System.currentTimeMillis()+time;
		userService.setBanToUser( username, new Date(newDate));
	}


}
