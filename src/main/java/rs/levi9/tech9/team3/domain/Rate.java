package rs.levi9.tech9.team3.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "rate")
public class Rate extends BaseEntity implements Serializable
{
	private static final long serialVersionUID = 2018902269409557939L;
	
	@Range(min=0, max=5)
	@Column(nullable = true)
	private Long mark;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "user_id", nullable = false)
	private User author;
	
	public Long getMark() {
		return mark;
	}

	public void setMark(Long mark) {
		this.mark = mark;
	}

	
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Rate() {
		
	}
     
	
	
}