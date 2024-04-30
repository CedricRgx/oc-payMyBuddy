package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.DTO.ProfileDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.service.IProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Implements IProfileService to manage user profiles within the PayMyBuddy application.
 */
@Slf4j
@Service
public class ProfileService implements IProfileService {

    @Autowired
    UserService userService;

    @Autowired
    UserAccountService userAccountService;

    /**
     * Retrieves the profile details of a specific user by their user ID.
     *
     * @param userId The ID of the user whose profile is being retrieved.
     * @return A ProfileDTO containing the user's profile information.
     */
    public ProfileDTO getProfile(Long userId){
        log.info("getProfile from the userId");
        Optional<User> user = userService.getUserById(userId);
        ProfileDTO profileDTO = ProfileDTO.builder()
                .email(user.get().getUserAccount().getEmail())
                .firstname(user.get().getFirstname())
                .lastname(user.get().getLastname())
                .birthdate(user.get().getBirthdate())
                .phone(user.get().getPhone())
                .address(user.get().getAddress())
                .build();
        return profileDTO;
    }

    /**
     * Updates the profile of a user based on the provided profile data transfer object (DTO).
     *
     * @param profileDTO A ProfileDTO containing the new profile information to be updated.
     * @return The updated User entity.
     * @throws UsernameNotFoundException if the user or user account cannot be found based on the provided email.
     */
    public User saveProfile(ProfileDTO profileDTO){
        log.info("updating an user profile");

        Optional<UserAccount> existingUserAccount = userAccountService.findByEmail(profileDTO.getEmail());
        if (!existingUserAccount.isPresent()) {
            throw new UsernameNotFoundException("UserAccount not found with email: " + profileDTO.getEmail());
        }
        UserAccount userAccount = existingUserAccount.get();
        userAccount.setLastConnectionDate(LocalDateTime.now());

        userAccountService.addUserAccount(userAccount);

        Long existingUserId = userService.getUserIdByEmail(profileDTO.getEmail());
        Optional<User> existingUser = userService.getUserById(existingUserId);
        if(existingUser == null){
            throw new UsernameNotFoundException("User not found with email: " + profileDTO.getEmail());
        } else if(!existingUser.isPresent()){
            throw new UsernameNotFoundException("User not found with ID: " + existingUserId);
        }
        User user = existingUser.get();
        user.setFirstname(profileDTO.getFirstname());
        user.setLastname(profileDTO.getLastname());
        user.setBirthdate(profileDTO.getBirthdate());
        user.setAddress(profileDTO.getAddress());
        user.setPhone(profileDTO.getPhone());
        userService.addUser(user);

        log.info("User profile updated successfully");

        return user;
    }
}
