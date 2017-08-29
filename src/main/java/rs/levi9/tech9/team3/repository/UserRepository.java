package rs.levi9.tech9.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.levi9.tech9.team3.domain.User;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsername(String username);
	public User findByEmail(String email);

	public List<User> findAllByBanExpirationDateIsNotNullAndBanExpirationDateAfterAndBanExpirationDateBefore(Date startDate,Date endDate);

}
