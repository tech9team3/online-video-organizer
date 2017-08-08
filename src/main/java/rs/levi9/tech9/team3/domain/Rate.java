package rs.levi9.tech9.team3.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "rate")
public class Rate extends BaseEntity implements Serializable
{
	private static final long serialVersionUID = 2018902269409557939L;
	
	@Range(min=0, max=5)
	@Column(nullable = true)
	private Long mark;

	public Long getMark() {
		return mark;
	}

	public void setMark(Long mark) {
		this.mark = mark;
	}

	public Rate() {
		
	}
     
	
	
}
