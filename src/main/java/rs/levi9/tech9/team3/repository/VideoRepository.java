package rs.levi9.tech9.team3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.domain.Video;
import rs.levi9.tech9.team3.domain.VideoList;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{
	public List<Video> getAllByVideoList(VideoList videoList);
	public List<Video> getAllByUser(User user);
	public List<Video> findByVisibleIsTrue();
	
	

}
