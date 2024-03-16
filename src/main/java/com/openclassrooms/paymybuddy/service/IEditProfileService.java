package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.DTO.ProfileDTO;
import com.openclassrooms.paymybuddy.model.User;

public interface IEditProfileService {

    public User saveProfile(ProfileDTO profileDTO);
}
