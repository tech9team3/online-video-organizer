package rs.levi9.tech9.team3.config;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.Role;
import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.repository.UserRepository;

@Transactional
@Service
public class OrganizerUserService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public OrganizerUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    	try {
	            User user = userRepository.findByUsername(username);
	            if (user == null) {
	                return null;
	            }
	            if (user.getStatus()) {
	            	return new  org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
	            }
	            return org.springframework.security.core.userdetails.User.
	            		withUsername(user.getUsername()).password(user.getPassword()).roles(getAuthorities(user).toString()).accountLocked(!user.getStatus()).build();
	        } catch (Exception e) {
	            throw new UsernameNotFoundException("User not found");
	        }
	    }
	  private Set<GrantedAuthority> getAuthorities(User user){
	        Set<GrantedAuthority> authorities = new HashSet<>();
	        for(Role role : user.getRoles()) {
	            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getType().toString());
	            authorities.add(grantedAuthority);
	        }
	        return authorities;
	    }
}
