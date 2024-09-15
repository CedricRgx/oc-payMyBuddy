package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.DTO.ProfileDTO;
import com.openclassrooms.paymybuddy.model.User;

/**
 * Interface for services related to managing user profiles within the PayMyBuddy application.
 */
public interface IProfileService {

    /**
     * Retrieves the profile of a user.
     *
     * @param userId The ID of the user whose profile to retrieve.
     * @return A ProfileDTO object containing the profile information.
     */
    public ProfileDTO getProfile(Long userId);

    /**
     * Saves or updates a user's profile.
     *
     * @param profileDTO The profile DTO containing the updated profile information.
     * @return The User object representing the updated user profile.
     */
    public User saveProfile(ProfileDTO profileDTO);

}