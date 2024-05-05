package com.openclassrooms.paymybuddy.config;

import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom implementation of the UserDetailsService for integration with the PayMyBuddy application's user account system.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Loads the user details required by Spring Security for authentication and authorization, based on the username provided.
     *
     * @param username the email address of the user trying to authenticate.
     * @return a UserDetails object containing necessary information for authentication and authorization.
     * @throws UsernameNotFoundException if no UserAccount is found with the provided email.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username).get();
        if(user==null){
            throw new UsernameNotFoundException("No user found with this email : " + username);
        }

        boolean enabled = user.getIsActive();
        if(enabled){
            userService.updateLastConnectionDate(user.getUserId());
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), enabled, true, true, true, getGrantedAuthorities(user.getRole()));
    }

    /**
     * Helper method to convert the user role into a Spring Security GrantedAuthority.
     * Prepends "ROLE_" to the role name to fit Spring Security conventions.
     *
     * @param role the role name of the user.
     * @return a list of GrantedAuthority based on the provided role.
     */
    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}