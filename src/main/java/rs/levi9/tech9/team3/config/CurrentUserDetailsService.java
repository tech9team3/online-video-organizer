package rs.levi9.tech9.team3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.CurrentUser;
import rs.levi9.tech9.team3.domain.User;
import rs.levi9.tech9.team3.service.UserService;

@Service
public class CurrentUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
    	   try {
               User user = userService.findOneByUsername(username);
               if (user == null) {
                   return null;
               }
               return new CurrentUser(user);
           } catch (Exception e) {
               throw new UsernameNotFoundException("User not found");
           }
      }
}