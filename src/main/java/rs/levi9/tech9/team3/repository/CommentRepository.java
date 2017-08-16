package rs.levi9.tech9.team3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.levi9.tech9.team3.domain.Comment;
import rs.levi9.tech9.team3.domain.Video;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	public List<Comment> findAllByVideo(Video video);
}
