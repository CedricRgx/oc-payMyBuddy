package com.openclassrooms.paymybuddy.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for the app account class.
 */
@Slf4j
class AppAccountTest {

    private static AppAccount appAccountTest;

    @BeforeAll
    public static void setUp(){
        appAccountTest = AppAccount.builder()
                .balance(42.3)
                .appOwner(
                    User.builder()
                        .firstname("Jean-Baptise")
                        .lastname("Poquelin")
                        .phone("1234567890")
                        .address("8 Main Street, 56098 Chemin")
                        .birthdate(LocalDate.of(2024, 7, 6))
                        .build())
                .build();
    }

    @Test
    public void testSetAndGetAppAccountId() {
        log.info("Running setAndGetAppAccountIdTest() test in AppAccountTest class");
        Long valueIdToTest = 2L;
        appAccountTest.setAppAccountId(valueIdToTest);
        assertEquals(valueIdToTest, appAccountTest.getAppAccountId());
    }

    @Test
    public void testSetAndGetBalance() {
        log.info("Running setAndGetBalance() test in AppAccountTest class");
        double valueDoubleToTest = 99.99;
        appAccountTest.setBalance(valueDoubleToTest);
        assertEquals(valueDoubleToTest, appAccountTest.getBalance());
    }

    @Test
    public void testEqualsSameInstance() {
        log.info("Running equalsSameInstanceTest() test in AppAccountTest class");
        AppAccount appAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(new User())
                .build();
        assertTrue(appAccount.equals(appAccount));
    }

    @Test
    public void testEqualsSameValues() {
        log.info("Running equalsSameValuesTest() test in AppAccountTest class");
        AppAccount appAccount1 = AppAccount.builder()
                .balance(100.0)
                .appOwner(null)
                .build();
        AppAccount appAccount2 = AppAccount.builder()
                .balance(100.0)
                .appOwner(null)
                .build();
        assertTrue(appAccount1.equals(appAccount2));
    }

    @Test
    public void testEqualsDifferentValues() {
        log.info("Running equalsDifferentValuesTest() test in AppAccountTest class");
        AppAccount appAccount1 = AppAccount.builder()
                .balance(100.0)
                .appOwner(null)
                .build();
        AppAccount appAccount2 = AppAccount.builder()
                .balance(200.0)
                .appOwner(null)
                .build();
        assertFalse(appAccount1.equals(appAccount2));
    }

    @Test
    public void testEqualsNullObject() {
        log.info("Running equalsNullObjectTest() test in AppAccountTest class");
        AppAccount appAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(null)
                .build();
        assertFalse(appAccount.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        log.info("Running equalsDifferentClassTest() test in AppAccountTest class");
        AppAccount appAccount = AppAccount.builder()
                .balance(100.0)
                .appOwner(null)
                .build();
        String differentClassObject = "This is not an AppAccount object";
        assertFalse(appAccount.equals(differentClassObject));
    }

    @Test
    public void testHashCode() {
        log.info("Running hashCodeTest() test in AppAccountTest class");
        AppAccount appAccount1 = AppAccount.builder()
                .balance(123.00)
                .appOwner(null)
                .build();
        AppAccount appAccount2 = AppAccount.builder()
                .balance(123.00)
                .appOwner(null)
                .build();
        assertThat(appAccount1.hashCode()).isEqualTo(appAccount2.hashCode());
        AppAccount appAccount3 = AppAccount.builder()
                .balance(456.00)
                .appOwner(null)
                .build();
        assertThat(appAccount1.hashCode()).isNotEqualTo(appAccount3.hashCode());
    }

    @Test
    public void testToString() {
        log.info("Running toStringTest() test in AppAccountTest class");
        AppAccount appAccount = AppAccount.builder()
                .balance(123.00)
                .appOwner(User.builder().firstname("Jean").lastname("Bon").build())
                .build();
        assertThat(appAccount.toString())
                .contains("AppAccount")
                .contains("appAccountId=" + appAccount.getAppAccountId())
                .contains("balance=" + appAccount.getBalance());
    }
}