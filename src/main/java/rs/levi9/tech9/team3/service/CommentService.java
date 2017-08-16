package rs.levi9.tech9.team3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.Comment;
import rs.levi9.tech9.team3.domain.Video;
import rs.levi9.tech9.team3.repository.CommentRepository;
import rs.levi9.tech9.team3.repository.VideoRepository;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private VideoRepository videoRepository;

	@Autowired
	public CommentService(CommentRepository commentRepository, VideoRepository videoRepository) {
		this.commentRepository = commentRepository;
		this.videoRepository = videoRepository;
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

	public List<Comment> findAllCommentsForVideo(Long videoId){
		Video foundVideo = videoRepository.findOne(videoId);
			List<Comment> listOfComments = commentRepository.findAllByVideo(foundVideo);
			return listOfComments;
	}
}
