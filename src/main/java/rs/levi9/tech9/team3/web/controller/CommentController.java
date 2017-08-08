package rs.levi9.tech9.team3.web.controller;

import java.awt.print.Book;
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
import rs.levi9.tech9.team3.domain.Comment;
import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.service.CommentService;
import rs.levi9.tech9.team3.service.UserService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	private CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Comment> findAll() {
		return commentService.findAll();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity findOne(@PathVariable("id") Long id) {
		Comment comment = commentService.findOne(id);
		if (comment == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(comment, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Comment save(@Valid @RequestBody Comment comment) {
		return commentService.save(comment);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		commentService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Comment put(@Valid @RequestBody Comment comment) {
		return commentService.save(comment);
	}

	@RequestMapping(path = "searchByUser/{user}", method = RequestMethod.GET)
	public List<Comment> findAllCommentsForUser(@PathVariable("user") String user) {
		return commentService.findAllCommentForUser(user);
	}

}
