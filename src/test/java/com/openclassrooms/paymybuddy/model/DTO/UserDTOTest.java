package com.openclassrooms.paymybuddy.model.DTO;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for the UserDTO class.
 */
@Slf4j
public class UserDTOTest {

    private UserDTO dto = UserDTO.builder()
            .firstname("John")
            .lastname("Doe")
            .address("123 Main St")
            .phone("1234567890")
            .balance("100.00").build();

    @Test
    public void testFirstnameGetter() {
        // Given
        String firstname = "John";

        // When
        String retrievedFirstname = dto.getFirstname();

        // Then
        assertEquals(firstname, retrievedFirstname);
    }

    @Test
    public void testFirstnameSetter() {
        // Given
        String firstname = "Mary";

        // When
        dto.setFirstname(firstname);

        // Then
        assertEquals(firstname, dto.getFirstname());
    }

    @Test
    public void testLastnameGetter() {
        // Given
        String lastname = "Doe";

        // When
        String retrievedLastname = dto.getLastname();

        // Then
        assertEquals(lastname, retrievedLastname);
    }

    @Test
    public void testLastnameSetter() {
        // Given
        String lastname = "Shelley";

        // When
        dto.setLastname(lastname);

        // Then
        assertEquals(lastname, dto.getLastname());
    }

    @Test
    public void testAddressGetter() {
        // Given
        String address = "123 Main St";

        // When
        String retrievedAddress = dto.getAddress();

        // Then
        assertEquals(address, retrievedAddress);
    }

    @Test
    public void testAddressSetter() {
        // Given
        String address = "123 Secondary St";

        // When
        dto.setAddress(address);

        // Then
        assertEquals(address, dto.getAddress());
    }

    @Test
    public void testPhoneGetter() {
        // Given
        String phone = "1234567890";

        // When
        String retrievedPhone = dto.getPhone();

        // Then
        assertEquals(phone, retrievedPhone);
    }

    @Test
    public void testPhoneSetter() {
        // Given
        String phone = "9876543210";

        // When
        dto.setPhone(phone);

        // Then
        assertEquals(phone, dto.getPhone());
    }

    @Test
    public void testBalanceGetter() {
        // Given
        String balance = "100.00";

        // When
        String retrievedBalance = dto.getBalance();

        // Then
        assertEquals(balance, retrievedBalance);
    }

    @Test
    public void testBalanceSetter() {
        // Given
        String balance = "200.00";

        // When
        dto.setBalance(balance);

        // Then
        assertEquals(balance, dto.getBalance());
    }

    @Test
    public void hashCode_withEqualObjects_shouldReturnEqualHashCodes() {
        // Given
        UserDTO dto1 = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();

        UserDTO dto2 = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();

        // When
        int hashCode1 = dto1.hashCode();
        int hashCode2 = dto2.hashCode();

        // Then
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void hashCode_withDifferentObjects_shouldReturnDifferentHashCodes() {
        // Given
        UserDTO dto1 = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();

        UserDTO dto2 = UserDTO.builder()
                .firstname("Jane")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();

        // When
        int hashCode1 = dto1.hashCode();
        int hashCode2 = dto2.hashCode();

        // Then
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    public void toString_shouldReturnCorrectStringRepresentation() {
        // Given
        UserDTO dto = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();

        // When
        String result = dto.toString();

        // Then
        String expected = "UserDTO(firstname=John, lastname=Doe, address=123 Main St, phone=1234567890, balance=100.00)";
        assertEquals(expected, result);
    }

    @Test
    public void equals_shouldReturnTrueForSameObject() {
        // Given
        UserDTO dto1 = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();

        // When
        boolean result = dto1.equals(dto1);

        // Then
        assertTrue(result);
    }

    @Test
    public void equals_shouldReturnTrueForEqualObjects() {
        // Given
        UserDTO dto1 = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();

        UserDTO dto2 = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertTrue(result);
    }

    @Test
    public void equals_shouldReturnFalseForDifferentObjects() {
        // Given
        UserDTO dto1 = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();

        UserDTO dto2 = UserDTO.builder()
                .firstname("Jane")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertFalse(result);
    }

    @Test
    public void equals_shouldReturnFalseForNullObject() {
        // Given
        UserDTO dto = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();

        // When
        boolean result = dto.equals(null);

        // Then
        assertFalse(result);
    }
}