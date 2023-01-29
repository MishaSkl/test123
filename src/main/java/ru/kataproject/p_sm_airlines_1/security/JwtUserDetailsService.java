package ru.kataproject.p_sm_airlines_1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kataproject.p_sm_airlines_1.entity.User;
import ru.kataproject.p_sm_airlines_1.security.jwt.JwtUser;
import ru.kataproject.p_sm_airlines_1.security.jwt.JwtUserFactory;
import ru.kataproject.p_sm_airlines_1.service.UserService;

/**
 * Implementation of {@link UserDetailsService} interface for {@link JwtUser}.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
