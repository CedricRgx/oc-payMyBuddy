package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.DTO.ProfileDTO;

public interface IProfileService {

    public ProfileDTO getProfile(Long userId);

}