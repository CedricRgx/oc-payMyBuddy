package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.User;

import java.util.List;

public interface IConnectionService {

    public List<User> findAllConnections();

    public List<User> searchConnections(String query);

    public boolean addConnection(Long userId, Long friendId);

    public boolean removeConnection(Long userId, Long friendId);
}
