package rs.levi9.tech9.team3.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "report")
public class Report extends BaseEntity implements Serializable {


	@OneToOne
	@JoinColumn(nullable = false)
	private User reportAuthor;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Comment reportedComment;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String reportText;

	public User getReportAuthor() {
		return reportAuthor;
	}

	public void setReportAuthor(User reportAuthor) {
		this.reportAuthor = reportAuthor;
	}

	public Comment getReportedComment() {
		return reportedComment;
	}

	public void setReportedComment(Comment reportedComment) {
		this.reportedComment = reportedComment;
	}

	public String getReportText() {
		return reportText;
	}

	public void setReportText(String reportText) {
		this.reportText = reportText;
	}

	public Report() {
	}

}
