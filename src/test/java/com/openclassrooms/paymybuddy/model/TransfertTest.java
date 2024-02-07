package com.openclassrooms.paymybuddy.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for the transfert class.
 */
@Slf4j
class TransfertTest {

    private static Transfert transfertTest;
    private static User authorTest;
    private static User recipientTest;

    @BeforeAll
    public static void setUp(){
        authorTest = User.builder()
                .firstname("Francis")
                .lastname("Bacon")
                .birthdate(LocalDate.of(1985, 9, 10))
                .phone("0987654321")
                .address("Test Street, 99999 VilleTest")
                .build();
        recipientTest = User.builder()
                .firstname("Francis")
                .lastname("Bacon")
                .birthdate(LocalDate.of(1985, 9, 10))
                .phone("0987654321")
                .address("Test Street, 99999 VilleTest")
                .build();
        transfertTest = Transfert.builder()
                .amount(42.3)
                .description("This is a good description !")
                .transactionDate(LocalDateTime.of(2024, 1, 23, 19, 15, 42, 1))
                .fee(5.00)
                .author(authorTest)
                .recipient(recipientTest)
                .build();
    }

    @Test
    public void setAndGetTransfertIdTest() {
        log.info("Running setAndGetTransfertIdTest() test in TransfertTest class");
        Long valueIdToTest = 2L;
        transfertTest.setTransactionId(valueIdToTest);
        assertEquals(valueIdToTest, transfertTest.getTransactionId());
    }

    @Test
    public void setAndGetAmountTest() {
        log.info("Running setAndGetAmountTest() test in TransfertTest class");
        double valueDoubleToTest = 99.99;
        transfertTest.setAmount(valueDoubleToTest);
        assertEquals(valueDoubleToTest, transfertTest.getAmount());
    }

    @Test
    public void setAndGetTransactionDateTest() {
        log.info("Running setAndGetTransactionDateTest() test in TransfertTest class");
        LocalDateTime valueDateToTest = LocalDateTime.of(2024,1 ,24, 20,20,20, 1);
        transfertTest.setTransactionDate(valueDateToTest);
        assertEquals(valueDateToTest, transfertTest.getTransactionDate());
    }

    @Test
    public void setAndGetFeeTest() {
        log.info("Running setAndGetFeeTest() test in TransfertTest class");
        double valueDoubleToTest = 99.99;
        transfertTest.setFee(valueDoubleToTest);
        assertEquals(valueDoubleToTest, transfertTest.getFee());
    }

    @Test
    public void setAndGetAuthorTest() {
        log.info("Running setAndGetAuthorTest() test in TransfertTest class");
        User authorToTest = User.builder()
                .firstname("Renée")
                .lastname("Bacon")
                .birthdate(LocalDate.of(1985, 9, 10))
                .phone("0987654321")
                .address("Test Street, 99999 VilleTest")
                .build();
        transfertTest.setAuthor(authorToTest);
        assertEquals(authorToTest, transfertTest.getAuthor());
    }

    @Test
    public void setAndGetRecipientTest() {
        log.info("Running setAndGetRecipientTest() test in TransfertTest class");
        User recipientToTest = User.builder()
                .firstname("Renée")
                .lastname("Bacon")
                .birthdate(LocalDate.of(1985, 9, 10))
                .phone("0987654321")
                .address("Test Street, 99999 VilleTest")
                .build();
        transfertTest.setRecipient(recipientToTest);
        assertEquals(recipientToTest, transfertTest.getRecipient());
    }

    @Test
    public void equalsSameInstanceTest() {
        log.info("Running equalsSameInstanceTest() test in TransfertTest class");
        Transfert transfert = Transfert.builder()
                .amount(123.00)
                .description("This is a description !")
                .transactionDate(LocalDateTime.of(2020,1, 23, 19, 15,42,1))
                .fee(5.00)
                .author(null)
                .recipient(null)
                .build();
        assertTrue(transfert.equals(transfert));
    }

    @Test
    public void equalsSameValuesTest() {
        log.info("Running equalsSameValuesTest() test in TransfertTest class");
        Transfert transfert1 = Transfert.builder()
                .amount(123.00)
                .description("This is a description !")
                .transactionDate(LocalDateTime.of(2020,1, 23, 19, 15,42,1))
                .fee(5.00)
                .author(null)
                .recipient(null)
                .build();
        Transfert transfert2 = Transfert.builder()
                .amount(123.00)
                .description("This is a description !")
                .transactionDate(LocalDateTime.of(2020,1, 23, 19, 15,42,1))
                .fee(5.00)
                .author(null)
                .recipient(null)
                .build();
        assertTrue(transfert1.equals(transfert2));
    }

//    @Test
//    public void equalsDifferentValuesTest() {
//        log.info("Running equalsDifferentValuesTest() test in TransfertTest class");
//        Transfert transfert1 = Transfert.builder()
//                .amount(111.00)
//                .description("This is a description !")
//                .transactionDate(LocalDateTime.of(2020,1, 23, 19, 15,42,1))
//                .fee(5.00)
//                .author(null)
//                .recipient(null)
//                .build();
//        Transfert transfert2 = Transfert.builder()
//                .amount(999.00)
//                .description("This is a description !")
//                .transactionDate(LocalDateTime.of(2020,1, 23, 19, 15,42,1))
//                .fee(5.00)
//                .author(null)
//                .recipient(null)
//                .build();
//        assertFalse(transfert1.equals(transfert2));
//    }

    @Test
    public void equalsNullObjectTest() {
        log.info("Running equalsNullObjectTest() test in TransfertTest class");
        Transfert transfert = Transfert.builder()
                .amount(123.00)
                .description("A big transfert")
                .transactionDate(LocalDateTime.of(2020, 1 , 23, 19, 15, 42, 1))
                .fee(5.00)
                .author(User.builder().firstname("Jean").lastname("Bon").build())
                .recipient(User.builder().firstname("Marron").lastname("Vert").build())
                .build();
        assertFalse(transfert.equals(null));
    }

    @Test
    public void equalsDifferentClassTest() {
        log.info("Running equalsDifferentClassTest() test in TransfertTest class");
        Transfert transfert = Transfert.builder()
                .amount(123.00)
                .description("A big transfert")
                .transactionDate(LocalDateTime.of(2020, 1 , 23, 19, 15, 42, 1))
                .fee(5.00)
                .author(User.builder().firstname("Jean").lastname("Bon").build())
                .recipient(User.builder().firstname("Marron").lastname("Vert").build())
                .build();
        String differentClassObject = "This is not an Transfert object";
        assertFalse(transfert.equals(differentClassObject));
    }

//    @Test
//    public void hashCodeTest() {
//        log.info("Running hashCodeTest() test in TransfertTest class");
//        Transfert transfert1 = Transfert.builder()
//                .amount(123.00)
//                .description("This is a description !")
//                .transactionDate(LocalDateTime.of(2020,1, 23, 19, 15,42,1))
//                .fee(5.00)
//                .author(null)
//                .recipient(null)
//                .build();
//        Transfert transfert2 = Transfert.builder()
//                .amount(999.00)
//                .description("This is a description !")
//                .transactionDate(LocalDateTime.of(2020,1, 23, 19, 15,42,1))
//                .fee(5.00)
//                .author(null)
//                .recipient(null)
//                .build();
//        assertThat(transfert1.hashCode()).isEqualTo(transfert2.hashCode());
//        Transfert transfert3 = Transfert.builder()
//                .amount(999.00)
//                .description("This is a description !")
//                .transactionDate(LocalDateTime.of(2020,1, 23, 19, 15,42,1))
//                .fee(5.00)
//                .author(null)
//                .recipient(null)
//                .build();
//        assertThat(transfert1.hashCode()).isNotEqualTo(transfert3.hashCode());
//    }

//    @Test
//    public void toStringTest() {
//        log.info("Running toStringTest() test in TransfertTest class");
//        Transfert transfert = Transfert.builder()
//                .amount(999.00)
//                .description("This is a description !")
//                .transactionDate(LocalDateTime.of(2020,1, 23, 19, 15,42,1))
//                .fee(5.00)
//                .author(null)
//                .recipient(null)
//                .build();
//        assertThat(transfert.toString())
//                .contains("Transfert")
//                .contains("transactionId=" + transfert.getTransactionId())
//                .contains("amount=" + transfert.getAmount())
//                .contains("description=" + transfert.getDescription())
//                .contains("transactionDate=" + transfert.getTransactionDate())
//                .contains("fee=" + transfert.getFee())
//                .contains("author=" + transfert.getAuthor())
//                .contains("recipient=" + transfert.getRecipient());
//    }
}