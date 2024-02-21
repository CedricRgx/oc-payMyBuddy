package com.openclassrooms.paymybuddy.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for the deposit class.
 */
@Slf4j
class DepositTest {

    private static Deposit depositTest;

    @BeforeAll
    public static void setUp(){
        depositTest = Deposit.builder()
                .amount(42.3)
                .description("Ceci est un dépôt")
                .transactionDate(LocalDateTime.of(2024, 1, 23, 19,15, 42,1))
                .fee(5.00)
                .author(null)
                .build();
    }

    @Test
    public void testSetAndGetTransactionId() {
        log.info("Running setAndGetTransactionIdTest() test in DepositTest class");
        Long valueIdToTest = 2L;
        depositTest.setTransactionId(valueIdToTest);
        assertEquals(valueIdToTest, depositTest.getTransactionId());
    }

    @Test
    public void testSetAndGetAmount() {
        log.info("Running setAndGetAmountTest() test in DepositTest class");
        double valueDoubleToTest = 99.99;
        depositTest.setAmount(valueDoubleToTest);
        assertEquals(valueDoubleToTest, depositTest.getAmount());
    }

    @Test
    public void testSetAndGetDescription() {
        log.info("Running setAndGetDescriptionTest() test in DepositTest class");
        String valueToTest = "Nouvelle description";
        depositTest.setDescription(valueToTest);
        assertEquals(valueToTest, depositTest.getDescription());
    }

    @Test
    public void testSetAndGetTransactionDate() {
        log.info("Running setAndGetTransactionDateTest() test in DepositTest class");
        LocalDateTime valueDateToTest = LocalDateTime.of(2024,1 ,24, 20,20,20, 1);
        depositTest.setTransactionDate(valueDateToTest);
        assertEquals(valueDateToTest, depositTest.getTransactionDate());
    }

    @Test
    public void testSetAndGetFee() {
        log.info("Running setAndGetFeeTest() test in DepositTest class");
        double valueDoubleToTest = 99.99;
        depositTest.setFee(valueDoubleToTest);
        assertEquals(valueDoubleToTest, depositTest.getFee());
    }

    @Test
    public void testSetAndGetAuthor() {
        log.info("Running setAndGetAuthorTest() test in DepositTest class");
        User depositorToTest = User.builder()
                .firstname("Jean-Baptise")
                .lastname("Poquelin")
                .phone("1234567890")
                .address("8 Main Street, 56098 Chemin")
                .birthdate(LocalDate.of(2024, 7, 6))
                .build();
        depositTest.setAuthor(depositorToTest);
        assertEquals(depositorToTest, depositTest.getAuthor());
    }

    @Test
    public void testEqualsSameInstance() {
        log.info("Running equalsSameInstanceTest() test in DepositTest class");
        Deposit deposit = Deposit.builder()
                .amount(42.3)
                .description("Ceci est un dépôt")
                .transactionDate(LocalDateTime.of(2024, 1, 23, 19,15, 42,1))
                .fee(5.00)
                .author(null)
                .build();
        assertEquals(deposit, deposit);
    }

    @Test
    public void testEqualsSameValues() {
        log.info("Running equalsSameValuesTest() test in DepositTest class");
        Deposit deposit1 = Deposit.builder()
                .amount(42.3)
                .description("Ceci est un dépôt")
                .transactionDate(LocalDateTime.of(2024, 1, 23, 19,15, 42,1))
                .fee(5.00)
                .author(null)
                .build();
        Deposit deposit2 = Deposit.builder()
                .amount(42.3)
                .description("Ceci est un dépôt")
                .transactionDate(LocalDateTime.of(2024, 1, 23, 19,15, 42,1))
                .fee(5.00)
                .author(null)
                .build();
        assertEquals(deposit1, deposit2);
    }

//    @Test
//    public void equalsDifferentValuesTest() {
//        log.info("Running equalsDifferentValuesTest() test in DepositTest class");
//        Deposit deposit1 = Deposit.builder()
//                .amount(42.3)
//                .description("Ceci est un dépôt")
//                .transactionDate(LocalDateTime.of(2024, 1, 23, 19,15, 42,1))
//                .fee(5.00)
//                .author(null)
//                .build();
//        Deposit deposit2 = Deposit.builder()
//                .amount(99.3)
//                .description("Ceci est un dépôt")
//                .transactionDate(LocalDateTime.of(2024, 1, 23, 19,15, 42,1))
//                .fee(5.00)
//                .author(null)
//                .build();
//        assertNotEquals(deposit1, deposit2);
//    }

    @Test
    public void testEqualsNullObject() {
        log.info("Running equalsNullObjectTest() test in DepositTest class");
        Deposit deposit = Deposit.builder()
                .amount(42.3)
                .description("Ceci est un dépôt")
                .transactionDate(LocalDateTime.of(2024, 1, 23, 19,15, 42,1))
                .fee(5.00)
                .author(null)
                .build();
        assertNotNull(deposit);
    }

    @Test
    public void testEqualsDifferentClass() {
        log.info("Running equalsDifferentClassTest() test in DepositTest class");
        Deposit deposit = Deposit.builder()
                .amount(42.3)
                .description("Ceci est un dépôt")
                .transactionDate(LocalDateTime.of(2024, 1, 23, 19,15, 42,1))
                .fee(5.00)
                .author(null)
                .build();
        String differentClassObject = "This is not an Deposit object";
        assertNotEquals(deposit, differentClassObject);
    }

//    @Test
//    public void hashCodeTest() {
//        log.info("Running hashCodeTest() test in DepositTest class");
//        Deposit deposit1 = Deposit.builder()
//                .amount(42.3)
//                .description("Ceci est un dépôt")
//                .transactionDate(LocalDateTime.of(2024, 1, 23, 19,15, 42,1))
//                .fee(5.00)
//                .author(null)
//                .build();
//        Deposit deposit2 = Deposit.builder()
//                .amount(42.3)
//                .description("Ceci est un dépôt")
//                .transactionDate(LocalDateTime.of(2024, 1, 23, 19,15, 42,1))
//                .fee(5.00)
//                .author(null)
//                .build();
//        assertThat(deposit1.hashCode()).isEqualTo(deposit2.hashCode());
//        Deposit deposit3 = Deposit.builder()
//                .amount(44.3)
//                .description("Ceci est un dépôt")
//                .transactionDate(LocalDateTime.of(2024, 1, 23, 19,15, 42,1))
//                .fee(5.00)
//                .author(null)
//                .build();
//        assertThat(deposit1.hashCode()).isNotEqualTo(deposit3.hashCode());
//    }

//    @Test
//    public void toStringTest() {
//        log.info("Running toStringTest() test in DepositTest class");
//        Deposit deposit = Deposit.builder()
//                .amount(42.3)
//                .description("Ceci est un dépôt")
//                .transactionDate(LocalDateTime.of(2024, 1, 23, 19,15, 42,1))
//                .fee(5.00)
//                .author(null)
//                .build();
//        assertThat(deposit.toString())
//                .contains("Deposit")
//                .contains("transactionId=" + deposit.getTransactionId())
//                .contains("amount=" + deposit.getAmount())
//                .contains("description=" + deposit.getDescription())
//                .contains("transactionDate=" + deposit.getTransactionDate())
//                .contains("fee=" + deposit.getFee())
//                .contains("author=" + deposit.getAuthor());
//    }
}