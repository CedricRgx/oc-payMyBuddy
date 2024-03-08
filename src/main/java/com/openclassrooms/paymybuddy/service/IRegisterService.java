package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.DTO.RegisterDTO;
import com.openclassrooms.paymybuddy.model.User;

public interface IRegisterService {

    public User addUser(RegisterDTO registerDTO);
}
