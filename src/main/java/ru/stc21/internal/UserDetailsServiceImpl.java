package ru.stc21.internal;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserDetailsServiceImpl implements UserDetailsService {

    private Map<String, ExtendedUser> userMap = new HashMap<>();

    public UserDetailsServiceImpl() {
        ExtendedUser user = new ExtendedUser("admin", "{noop}1",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN2")),
                "000-123-123 77", LocalDate.of(2000, 1, 1));
        userMap.put(user.getUsername().toLowerCase(), user);

        user = new ExtendedUser("user", "{noop}1",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER3")),
                "000-132-132 55", LocalDate.of(2005, 1, 1));
        userMap.put(user.getUsername().toLowerCase(), user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userMap.get(s.toLowerCase());
    }
}
