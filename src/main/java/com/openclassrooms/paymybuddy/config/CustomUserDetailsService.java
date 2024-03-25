package com.openclassrooms.paymybuddy.config;

import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import com.openclassrooms.paymybuddy.service.impl.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom implementation of the UserDetailsService for integration with the PayMyBuddy application's user account system.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountService userAccountService;

    /**
     * Loads the user details required by Spring Security for authentication and authorization, based on the username provided.
     *
     * @param username the email address of the user trying to authenticate.
     * @return a UserDetails object containing necessary information for authentication and authorization.
     * @throws UsernameNotFoundException if no UserAccount is found with the provided email.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByEmail(username);
        if(userAccount==null){
            throw new UsernameNotFoundException("No user found with this email : " + username);
        }

        boolean enabled = userAccount.getIsActive();
        if(enabled){
            userAccountService.updateLastConnectionDate(userAccount.getUserAccountId());
        }

        return new User(userAccount.getEmail(), userAccount.getPassword(), enabled, true, true, true, getGrantedAuthorities(userAccount.getRole()));
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