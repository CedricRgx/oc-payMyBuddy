package com.openclassrooms.paymybuddy.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for the user class.
 */
@Slf4j
class UserTest {

    private static User userTest;
    private String valueToTest;

    @BeforeAll
    public static void setUp(){
        userTest = User.builder()
                .firstname("Francis")
                .lastname("Bacon")
                .build();
    }

    @Test
    public void setAndGetUserIdTest(){
        log.info("Running setAndGetUserIdTest() test in UserTest class");
        int valueIdToTest = 1;
        userTest.setUserId(valueIdToTest);
        assertEquals(valueIdToTest, userTest.getUserId());
    }

    @Test
    public void setAndGetFirstnameTest(){
        log.info("Running setAndGetFirstnameTest() test in UserTest class");
        valueToTest = "John";
        userTest.setFirstname(valueToTest);
        assertEquals(valueToTest, userTest.getFirstname());
    }

    @Test
    public void setAndGetLastnameTest(){
        log.info("Running setAndGetLastnameTest() test in UserTest class");
        valueToTest = "Newton";
        userTest.setLastname(valueToTest);
        assertEquals(valueToTest, userTest.getLastname());
    }

    @Test
    public void setAndGetAppAccountTest(){
        log.info("Running setAndGetAppAccountTest() test in UserTest class");
        User user = new User();
        AppAccount appAccountTest = new AppAccount();
        appAccountTest.setBalance(3.14);
        user.setAppAccount(appAccountTest);
        assertEquals(appAccountTest, user.getAppAccount());
    }

    @Test
    public void userAccountAssociationTest() {
        log.info("Running userAccountAssociationTest() test in UserTest class");
        UserAccount userAccount = new UserAccount();
        userTest.setUserAccount(userAccount);
        assertEquals(userAccount, userTest.getUserAccount());
    }

    @Test
    public void friendsAssociationTest() {
        log.info("Running friendsAssociationTest() test in UserTest class");
        List<User> friends = Arrays.asList(new User(), new User());
        userTest.setFriends(friends);
        assertEquals(friends, userTest.getFriends());
    }

    @Test
    public void sentTransfertsAssociationTest() {
        log.info("Running sentTransfertsAssociationTest() test in UserTest class");
        List<Transfert> sentTransferts = Arrays.asList(new Transfert(), new Transfert());
        userTest.setSentTransferts(sentTransferts);
        assertEquals(sentTransferts, userTest.getSentTransferts());
    }

    @Test
    public void receivedTransfertsAssociationTest() {
        log.info("Running receivedTransfertsAssociationTest() test in UserTest class");
        List<Transfert> receivedTransferts = Arrays.asList(new Transfert(), new Transfert());
        userTest.setReceivedTransferts(receivedTransferts);
        assertEquals(receivedTransferts, userTest.getReceivedTransferts());
    }


    @Test
    public void depositsAssociationTest() {
        log.info("Running depositsAssociationTest() test in UserTest class");
        List<Deposit> deposits = Arrays.asList(new Deposit(), new Deposit());
        userTest.setDeposits(deposits);
        assertEquals(deposits, userTest.getDeposits());
    }

    @Test
    public void equalsSameInstanceTest() {
        log.info("Running equalsSameInstanceTest() test in UserTest class");
        User user = User.builder().firstname("John").lastname("Newton").build();
        assertTrue(user.equals(user));
    }

    @Test
    public void equalsSameValuesTest() {
        log.info("Running equalsSameValuesTest() test in UserTest class");
        User user1 = User.builder().firstname("John").lastname("Newton").build();
        User user2 = User.builder().firstname("John").lastname("Newton").build();
        assertTrue(user1.equals(user2));
    }

    @Test
    public void equalsDifferentValuesTest() {
        log.info("Running equalsDifferentValuesTest() test in UserTest class");
        User user1 = User.builder().firstname("John").lastname("Newton").build();
        User user2 = User.builder().firstname("Jean").lastname("Newton").build();
        assertFalse(user1.equals(user2));
    }

    @Test
    public void equalsNullObjectTest() {
        log.info("Running equalsNullObjectTest() test in UserTest class");
        User user = User.builder().firstname("John").lastname("Newton").build();
        assertFalse(user.equals(null));
    }

    @Test
    public void equalsDifferentClassTest() {
        log.info("Running equals_DifferentClassTest() test in UserTest class");
        User user = User.builder().firstname("John").lastname("Newton").build();
        String differentClassObject = "This is not an User object";
        assertFalse(user.equals(differentClassObject));
    }

    @Test
    public void toStringTest() {
        log.info("Running toStringTest() test in UserTest class");
        User user = User.builder().firstname("Francis").lastname("Bacon").build();
        assertThat(user.toString())
                .contains("User")
                .contains("userId=" + user.getUserId())
                .contains("firstname=" + user.getFirstname())
                .contains("lastname=" + user.getLastname());
    }

    @Test
    public void equalsTest() {
        log.info("Running equalsTest() test in UserTest class");
        User user1 = User.builder().firstname("John").lastname("Newton").build();
        User user2 = User.builder().firstname("John").lastname("Newton").build();
        assertThat(user1.equals(user2)).isTrue();
        assertThat(user2.equals(user1)).isTrue();
        User user3 = User.builder().firstname("Une").lastname("Pomme").build();
        assertThat(user1.equals(user3)).isFalse();
    }

}