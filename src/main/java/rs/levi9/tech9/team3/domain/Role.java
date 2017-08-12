package rs.levi9.tech9.team3.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends BaseEntity  {

	@Enumerated(EnumType.STRING)
	private RoleType type;

	public RoleType getType() {
		return type;
	}

	public void setType(RoleType type) {
		this.type = type;
	}


	public Role() {
	}

	public enum RoleType {
		ROLE_USER, ROLE_ADMIN;

	}
}
