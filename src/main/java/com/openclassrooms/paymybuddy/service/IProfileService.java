package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.DTO.ProfileDTO;
import com.openclassrooms.paymybuddy.model.User;

public interface IProfileService {

    public ProfileDTO getProfile(Long userId);

    public User saveProfile(ProfileDTO profileDTO);

}