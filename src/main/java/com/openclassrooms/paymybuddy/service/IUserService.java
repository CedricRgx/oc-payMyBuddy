package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.DTO.ConnectionDTO;
import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public Iterable<User> getUsers();

    public Optional<User> getUserById(Long id);

    public User addUser(User user);

    public void deleteUserById(Long id);

    public Long getUserIdByEmail(String email);

    public List<ConnectionDTO> getActiveFriends(List<User> listOfFriends);

    public UserDTO getUserDTOFromUser(String email);

}
