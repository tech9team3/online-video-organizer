package rs.levi9.tech9.team3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.Comment;
import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.repository.CommentRepository;
import rs.levi9.tech9.team3.repository.UserRepository;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private UserRepository userRepository;

	@Autowired
	public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
		this.commentRepository = commentRepository;
		this.userRepository = userRepository;
	}

	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	public Comment findOne(Long id) {
		return commentRepository.findOne(id);
	}

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	public void delete(Long id) {
		commentRepository.delete(id);
	}

	public List<Comment> findAllCommentForUser(String author) {
		User foundUser = userRepository.findByUsername(author);
		return commentRepository.findAllByAuthor(foundUser);
	}
}
