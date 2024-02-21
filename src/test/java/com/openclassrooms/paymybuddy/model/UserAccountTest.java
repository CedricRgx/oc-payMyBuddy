package com.openclassrooms.paymybuddy.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for the user Account class.
 */
@Slf4j
class UserAccountTest {

    private static User userTest;
    private static UserAccount userAccountTest;
    private String valueToTest;

    @BeforeAll
    public static void setUp(){
        userAccountTest = UserAccount.builder()
                .email("ceci@mail.com")
                .password("motdepasse")
                .lastConnectionDate(LocalDateTime.of(2024,
                                1 ,
                                23,
                                19,
                                15,
                                42,
                                1))
                .isActive(true)
                .build();
    }

    @Test
    public void TestSetAndGetUserAccountId() {
        log.info("Running setAndGetUserAccountId() test in UserAccountTest class");
        Long valueIdToTest = 1L;
        userAccountTest.setUserAccountId(valueIdToTest);
        assertEquals(valueIdToTest, userAccountTest.getUserAccountId());
    }

    @Test
    public void testSetAndGetEmail() {
        log.info("Running setAndGetEmail() test in UserAccountTest class");
        valueToTest = "mail@emmail.com";
        userAccountTest.setEmail(valueToTest);
        assertEquals(valueToTest, userAccountTest.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        log.info("Running setAndGetPassword() test in UserAccountTest class");
        valueToTest = "superMotDePasse";
        userAccountTest.setPassword(valueToTest);
        assertEquals(valueToTest, userAccountTest.getPassword());
    }

    @Test
    public void testSetAndGetLastConnectionDate() {
        log.info("Running setAndGetLastConnectionDate() test in UserAccountTest class");
        LocalDateTime valueDateToTest = LocalDateTime.of(2024,1 ,24, 20,20,20, 1);
        userAccountTest.setLastConnectionDate(valueDateToTest);
        assertEquals(valueDateToTest, userAccountTest.getLastConnectionDate());
    }

    @Test
    public void testSetAndGetIsActive() {
        log.info("Running setAndGetIsActive() test in UserAccountTest class");
        Boolean valueBooleanToTest = false;
        userAccountTest.setIsActive(valueBooleanToTest);
        assertEquals(valueBooleanToTest, userAccountTest.getIsActive());
    }

    @Test
    public void testEqualsSameInstance() {
        log.info("Running equalsSameInstanceTest() test in UserAccountTest class");
        UserAccount userAccount = UserAccount.builder().email("emailOne@mail.com").password("motDePasseOne").lastConnectionDate(null).isActive(true).build();
        assertTrue(userAccount.equals(userAccount));
    }

    @Test
    public void testEqualsSameValues() {
        log.info("Running equalsSameValuesTest() test in UserAccountTest class");
        UserAccount userAccount1 = UserAccount.builder().email("emailOne@mail.com").password("motDePasseOne").lastConnectionDate(null).isActive(true).build();
        UserAccount userAccount2 = UserAccount.builder().email("emailOne@mail.com").password("motDePasseOne").lastConnectionDate(null).isActive(true).build();
        assertTrue(userAccount1.equals(userAccount2));
    }

    @Test
    public void testEqualsDifferentValues() {
        log.info("Running equalsDifferentValuesTest() test in UserAccountTest class");
        UserAccount userAccount1 = UserAccount.builder().email("emailOne@mail.com").password("motDePasseOne").lastConnectionDate(null).isActive(true).build();
        UserAccount userAccount2 = UserAccount.builder().email("emailTwo@mail.com").password("motDePasseOne").lastConnectionDate(null).isActive(true).build();
        assertFalse(userAccount1.equals(userAccount2));
    }

    @Test
    public void testEqualsNullObject() {
        log.info("Running equalsNullObjectTest() test in UserAccountTest class");
        UserAccount userAccount = UserAccount.builder().email("emailOne@mail.com").password("motDePasseOne").lastConnectionDate(null).isActive(true).build();
        assertFalse(userAccount.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        log.info("Running equalsDifferentClassTest() test in UserAccountTest class");
        UserAccount userAccount = UserAccount.builder().email("emailOne@mail.com").password("motDePasseOne").lastConnectionDate(null).isActive(true).build();
        String differentClassObject = "This is not an UserAccount object";
        assertFalse(userAccount.equals(differentClassObject));
    }

    @Test
    public void testHashCode() {
        log.info("Running hashCodeTest() test in UserAccountTest class");
        UserAccount userAccount1 = UserAccount.builder().email("emailOne@mail.com").password("motDePasseOne").lastConnectionDate(null).isActive(true).build();
        UserAccount userAccount2 = UserAccount.builder().email("emailOne@mail.com").password("motDePasseOne").lastConnectionDate(null).isActive(true).build();
        assertThat(userAccount1.hashCode()).isEqualTo(userAccount2.hashCode());
        UserAccount userAccount3 = UserAccount.builder().email("emailThree@mail.com").password("motDePasseThree").lastConnectionDate(null).isActive(true).build();
        assertThat(userAccount1.hashCode()).isNotEqualTo(userAccount3.hashCode());
    }

    @Test
    public void testToString() {
        log.info("Running toStringTest() test in UserAccountTest class");
        UserAccount userAccount = UserAccount.builder().email("email@mail.com").password("motDePasse").lastConnectionDate(null).isActive(true).build();
        assertThat(userAccount.toString())
                .contains("UserAccount")
                .contains("userAccountId=" + userAccount.getUserAccountId())
                .contains("email=" + userAccount.getEmail())
                .contains("password=" + userAccount.getPassword())
                .contains("lastConnectionDate=" + userAccount.getLastConnectionDate())
                .contains("isActive=" + userAccount.getIsActive());
    }
}