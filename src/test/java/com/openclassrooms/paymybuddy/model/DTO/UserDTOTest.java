package com.openclassrooms.paymybuddy.model.DTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
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
    public void testHashCode_withEqualObjects_shouldReturnEqualHashCodes() {
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
    public void testHashCode_withDifferentObjects_shouldReturnDifferentHashCodes() {
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
    public void testToString_shouldReturnCorrectStringRepresentation() {
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
    public void testEquals_shouldReturnTrueForSameObject() {
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
    public void testEquals_shouldReturnTrueForEqualObjects() {
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
    public void testEquals_shouldReturnFalseForDifferentObjects() {
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
    public void testEquals_shouldReturnFalseForNullObject() {
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

    @Test
    public void testHashCode_withNullFields_shouldHandleNullsGracefully() {
        // Given
        UserDTO nullFirstname = UserDTO.builder()
                .firstname(null)
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();
        UserDTO nullLastname = UserDTO.builder()
                .firstname("John")
                .lastname(null)
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();
        UserDTO nullAddress = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address(null)
                .phone("1234567890")
                .balance("100.00")
                .build();
        UserDTO nullPhone = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone(null)
                .balance("100.00")
                .build();
        UserDTO nullBalance = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance(null)
                .build();

        // When Then
        assertDoesNotThrow(nullFirstname::hashCode);
        assertDoesNotThrow(nullLastname::hashCode);
        assertDoesNotThrow(nullAddress::hashCode);
        assertDoesNotThrow(nullPhone::hashCode);
        assertDoesNotThrow(nullBalance::hashCode);
    }

    @Test
    public void testHashCode_consistencyCheck() {
        // Given
        int initialHashCode = dto.hashCode();

        // When Then
        assertEquals(initialHashCode, dto.hashCode());
        assertEquals(initialHashCode, dto.hashCode());
    }

    @Test
    public void testHashCode_withDifferentFields_shouldReturnDifferentHashCodes() {
        // Given
        UserDTO baseDTO = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();
        UserDTO differentFirstname = UserDTO.builder()
                .firstname("Jane")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();
        UserDTO differentLastname = UserDTO.builder()
                .firstname("John")
                .lastname("Smith")
                .address("123 Main St")
                .phone("1234567890")
                .balance("100.00")
                .build();
        UserDTO differentAddress = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("456 Elm St")
                .phone("1234567890")
                .balance("100.00")
                .build();
        UserDTO differentPhone = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone("0987654321")
                .balance("100.00")
                .build();
        UserDTO differentBalance = UserDTO.builder()
                .firstname("John")
                .lastname("Doe")
                .address("123 Main St")
                .phone("1234567890")
                .balance("200.00")
                .build();

        // When Then
        assertNotEquals(baseDTO.hashCode(), differentFirstname.hashCode());
        assertNotEquals(baseDTO.hashCode(), differentLastname.hashCode());
        assertNotEquals(baseDTO.hashCode(), differentAddress.hashCode());
        assertNotEquals(baseDTO.hashCode(), differentPhone.hashCode());
        assertNotEquals(baseDTO.hashCode(), differentBalance.hashCode());
    }

}