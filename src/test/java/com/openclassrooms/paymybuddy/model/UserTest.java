package com.openclassrooms.paymybuddy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Autowired
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testUserIdSetter() {
        // Given
        Long userId = 123L;

        // When
        user.setUserId(userId);

        // Then
        assertEquals(userId, user.getUserId());
    }

    @Test
    public void testUserIdGetter() {
        // Given
        Long userId = 123L;
        user.setUserId(userId);

        // When
        Long retrievedUserId = user.getUserId();

        // Then
        assertEquals(userId, retrievedUserId);
    }

    @Test
    public void testFirstnameSetter() {
        // Given
        String firstname = "John";

        // When
        user.setFirstname(firstname);

        // Then
        assertEquals(firstname, user.getFirstname());
    }

    @Test
    public void testFirstnameGetter() {
        // Given
        String firstname = "John";
        user.setFirstname(firstname);

        // When
        String retrievedFirstname = user.getFirstname();

        // Then
        assertEquals(firstname, retrievedFirstname);
    }

    @Test
    public void testLastnameSetter() {
        // Given
        String lastname = "Doe";

        // When
        user.setLastname(lastname);

        // Then
        assertEquals(lastname, user.getLastname());
    }

    @Test
    public void testLastnameGetter() {
        // Given
        String lastname = "Doe";
        user.setLastname(lastname);

        // When
        String retrievedLastname = user.getLastname();

        // Then
        assertEquals(lastname, retrievedLastname);
    }

    @Test
    public void testBirthdateSetter() {
        // Given
        LocalDate birthdate = LocalDate.of(1990, 1, 1);

        // When
        user.setBirthdate(birthdate);

        // Then
        assertEquals(birthdate, user.getBirthdate());
    }

    @Test
    public void testBirthdateGetter() {
        // Given
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        user.setBirthdate(birthdate);

        // When
        LocalDate retrievedBirthdate = user.getBirthdate();

        // Then
        assertEquals(birthdate, retrievedBirthdate);
    }

    @Test
    public void testPhoneSetter() {
        // Given
        String phone = "123456789";

        // When
        user.setPhone(phone);

        // Then
        assertEquals(phone, user.getPhone());
    }

    @Test
    public void testPhoneGetter() {
        // Given
        String phone = "123456789";
        user.setPhone(phone);

        // When
        String retrievedPhone = user.getPhone();

        // Then
        assertEquals(phone, retrievedPhone);
    }

    @Test
    public void testAddressSetter() {
        // Given
        String address = "123 Main St";

        // When
        user.setAddress(address);

        // Then
        assertEquals(address, user.getAddress());
    }

    @Test
    public void testAddressGetter() {
        // Given
        String address = "123 Main St";
        user.setAddress(address);

        // When
        String retrievedAddress = user.getAddress();

        // Then
        assertEquals(address, retrievedAddress);
    }

    @Test
    public void testUserAccountSetter() {
        // Given
        UserAccount userAccount = new UserAccount();

        // When
        user.setUserAccount(userAccount);

        // Then
        assertEquals(userAccount, user.getUserAccount());
    }

    @Test
    public void testUserAccountGetter() {
        // Given
        UserAccount userAccount = new UserAccount();
        user.setUserAccount(userAccount);

        // When
        UserAccount retrievedUserAccount = user.getUserAccount();

        // Then
        assertEquals(userAccount, retrievedUserAccount);
    }

    @Test
    public void testAppAccountSetter() {
        // Given
        AppAccount appAccount = new AppAccount();

        // When
        user.setAppAccount(appAccount);

        // Then
        assertEquals(appAccount, user.getAppAccount());
    }

    @Test
    public void testAppAccountGetter() {
        // Given
        AppAccount appAccount = new AppAccount();
        user.setAppAccount(appAccount);

        // When
        AppAccount retrievedAppAccount = user.getAppAccount();

        // Then
        assertEquals(appAccount, retrievedAppAccount);
    }

    @Test
    public void testFriendsSetter() {
        // Given
        List<User> friends = new ArrayList<>();
        User friend1 = new User();
        User friend2 = new User();
        friends.add(friend1);
        friends.add(friend2);

        // When
        user.setFriends(friends);

        // Then
        assertEquals(friends, user.getFriends());
    }

    @Test
    public void testFriendsGetter() {
        // Given
        List<User> friends = new ArrayList<>();
        User friend1 = new User();
        User friend2 = new User();
        friends.add(friend1);
        friends.add(friend2);
        user.setFriends(friends);

        // When
        List<User> retrievedFriends = user.getFriends();

        // Then
        assertEquals(friends, retrievedFriends);
    }

    @Test
    public void testSourceTransacSetter() {
        // Given
        List<Deposit> sourceTransac = new ArrayList<>();
        Deposit deposit1 = new Deposit();
        Deposit deposit2 = new Deposit();
        sourceTransac.add(deposit1);
        sourceTransac.add(deposit2);

        // When
        user.setSourceTransac(sourceTransac);

        // Then
        assertEquals(sourceTransac, user.getSourceTransac());
    }

    @Test
    public void testSourceTransacGetter() {
        // Given
        List<Deposit> sourceTransac = new ArrayList<>();
        Deposit deposit1 = new Deposit();
        Deposit deposit2 = new Deposit();
        sourceTransac.add(deposit1);
        sourceTransac.add(deposit2);
        user.setSourceTransac(sourceTransac);

        // When
        List<Deposit> retrievedSourceTransac = user.getSourceTransac();

        // Then
        assertEquals(sourceTransac, retrievedSourceTransac);
    }

    @Test
    public void testReceivedTransfertsSetter() {
        // Given
        List<Transfert> receivedTransferts = new ArrayList<>();
        Transfert transfert1 = new Transfert();
        Transfert transfert2 = new Transfert();
        receivedTransferts.add(transfert1);
        receivedTransferts.add(transfert2);

        // When
        user.setReceivedTransferts(receivedTransferts);

        // Then
        assertEquals(receivedTransferts, user.getReceivedTransferts());
    }

    @Test
    public void testReceivedTransfertsGetter() {
        // Given
        List<Transfert> receivedTransferts = new ArrayList<>();
        Transfert transfert1 = new Transfert();
        Transfert transfert2 = new Transfert();
        receivedTransferts.add(transfert1);
        receivedTransferts.add(transfert2);
        user.setReceivedTransferts(receivedTransferts);

        // When
        List<Transfert> retrievedReceivedTransferts = user.getReceivedTransferts();

        // Then
        assertEquals(receivedTransferts, retrievedReceivedTransferts);
    }

    @Test
    public void testBuilder() {
        // Given
        String firstname = "John";
        String lastname = "Doe";
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        String phone = "123456789";
        String address = "123 Main St";
        AppAccount appAccount = new AppAccount();
        UserAccount userAccount = new UserAccount();

        // When
        User builtUser = User.builder()
                .firstname(firstname)
                .lastname(lastname)
                .birthdate(birthdate)
                .phone(phone)
                .address(address)
                .appAccount(appAccount)
                .userAccount(userAccount)
                .build();

        // Then
        assertNotNull(builtUser);
        assertEquals(firstname, builtUser.getFirstname());
        assertEquals(lastname, builtUser.getLastname());
        assertEquals(birthdate, builtUser.getBirthdate());
        assertEquals(phone, builtUser.getPhone());
        assertEquals(address, builtUser.getAddress());
        assertEquals(appAccount, builtUser.getAppAccount());
        assertEquals(userAccount, builtUser.getUserAccount());
    }
}
