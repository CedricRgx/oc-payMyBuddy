package com.openclassrooms.paymybuddy.service.ti;

import com.openclassrooms.paymybuddy.model.AppAccount;
import com.openclassrooms.paymybuddy.model.DTO.ConnectionDTO;
import com.openclassrooms.paymybuddy.model.DTO.UserDTO;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserAccount;
import com.openclassrooms.paymybuddy.repository.AppAccountRepository;
import com.openclassrooms.paymybuddy.repository.UserRepository;
import com.openclassrooms.paymybuddy.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
class UserServiceIntegrationTest {



    /*

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AppAccountRepository appAccountRepository;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testGetUsers() {
        List<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        Iterable<User> result = userService.getUsers();

        assertEquals(userList, result);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(userId);

        assertEquals(Optional.of(user), result);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.addUser(user);

        assertEquals(user, result);
    }

    @Test
    public void testDeleteUserById() {
        Long userId = 1L;
        userService.deleteUserById(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testGetUserIdByEmail() {
        String email = "john.doe@example.com";
        Long expectedUserId = 1L;
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Long.class))).thenReturn(expectedUserId);

        Long result = userService.getUserIdByEmail(email);

        assertEquals(expectedUserId, result);
    }

    @Test
    public void testGetActiveFriends() {
        User user1 = new User();
        user1.setUserAccount(new UserAccount());
        user1.getUserAccount().setIsActive(true);

        User user2 = new User();
        user2.setUserAccount(new UserAccount());
        user2.getUserAccount().setIsActive(false);

        List<User> listOfFriends = new ArrayList<>();
        listOfFriends.add(user1);
        listOfFriends.add(user2);

        List<ConnectionDTO> expectedList = new ArrayList<>();
        expectedList.add(ConnectionDTO.builder()
                .userId(user1.getUserId())
                .firstname(user1.getFirstname())
                .lastname(user1.getLastname())
                .build());

        List<ConnectionDTO> result = userService.getActiveFriends(listOfFriends);

        assertEquals(expectedList, result);
    }

    @Test
    public void testGetUserBalance() {
        Long userId = 1L;
        Double expectedBalance = 100.0;
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Double.class))).thenReturn(expectedBalance);

        Double result = userService.getUserBalance(userId);

        assertEquals(expectedBalance, result);
    }

    @Test
    public void testCreditUserBalance_UserNotFound() {
        Long userId = 1L;
        double amount = 50.0;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.creditUserBalance(userId, amount));
    }

    @Test
    public void testCreditUserBalance_AppAccountNotFound() {
        Long userId = 1L;
        double amount = 50.0;

        User user = new User();
        user.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        assertThrows(IllegalStateException.class, () -> userService.creditUserBalance(userId, amount));
    }
    */

}