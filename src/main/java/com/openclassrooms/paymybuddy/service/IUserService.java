package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.User;

import java.util.Optional;

public interface IUserService {

    public Iterable<User> getUsers();

    public Optional<User> getUserById(Long id);

    public User addUser(User user);

    public void deleteUserById(Long id);

}
