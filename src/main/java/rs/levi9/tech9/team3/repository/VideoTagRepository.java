package rs.levi9.tech9.team3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.levi9.tech9.team3.domain.Video;
import rs.levi9.tech9.team3.domain.VideoTag;

@Repository
public interface VideoTagRepository extends JpaRepository<VideoTag, Long> {




}
