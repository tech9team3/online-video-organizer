package rs.levi9.tech9.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.levi9.tech9.team3.domain.Comment;
import rs.levi9.tech9.team3.domain.Report;
import rs.levi9.tech9.team3.domain.User;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    public Report findByReportedComment(Comment comment);
    public List<Report> findByReportAuthor(User user);
}
