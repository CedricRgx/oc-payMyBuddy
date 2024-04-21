package com.openclassrooms.paymybuddy.model.DTO;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ConnectionDTOTest {

    private ConnectionDTO connectionDTO = ConnectionDTO.builder()
            .userId(123L)
            .firstname("John")
            .lastname("Doe").build();

    @Test
    public void testUserIdSetter() {
        // Given
        Long userId = 123L;

        // When
        connectionDTO.setUserId(userId);

        // Then
        assertEquals(userId, connectionDTO.getUserId());
    }

    @Test
    public void testUserIdGetter() {
        // When
        Long retrievedUserId = connectionDTO.getUserId();

        // Then
        assertEquals(123L, retrievedUserId);
    }

    @Test
    public void testFirstnameSetter() {
        // Given
        String firstname = "John";

        // When
        connectionDTO.setFirstname(firstname);

        // Then
        assertEquals(firstname, connectionDTO.getFirstname());
    }

    @Test
    public void testFirstnameGetter() {
        // When
        String retrievedFirstname = connectionDTO.getFirstname();

        // Then
        assertEquals("John", retrievedFirstname);
    }

    @Test
    public void testLastnameSetter() {
        // Given
        String lastname = "Doe";

        // When
        connectionDTO.setLastname(lastname);

        // Then
        assertEquals(lastname, connectionDTO.getLastname());
    }

    @Test
    public void testLastnameGetter() {
        // When
        String retrievedLastname = connectionDTO.getLastname();

        // Then
        assertEquals("Doe", retrievedLastname);
    }

    @Test
    public void hashCode_equalInstances_shouldReturnSameHashCode() {
        // Given
        ConnectionDTO dto1 = ConnectionDTO.builder()
                .userId(1L)
                .firstname("John")
                .lastname("Doe")
                .build();

        ConnectionDTO dto2 = ConnectionDTO.builder()
                .userId(1L)
                .firstname("John")
                .lastname("Doe")
                .build();

        // When
        int hashCode1 = dto1.hashCode();
        int hashCode2 = dto2.hashCode();

        // Then
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void hashCode_differentInstances_shouldReturnDifferentHashCodes() {
        // Given
        ConnectionDTO dto1 = ConnectionDTO.builder()
                .userId(1L)
                .firstname("John")
                .lastname("Doe")
                .build();

        ConnectionDTO dto2 = ConnectionDTO.builder()
                .userId(2L) // Different user ID
                .firstname("John")
                .lastname("Doe")
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
        ConnectionDTO dto = ConnectionDTO.builder()
                .userId(1L)
                .firstname("John")
                .lastname("Doe")
                .build();

        // When
        String expectedString = "ConnectionDTO(userId=1, firstname=John, lastname=Doe)";
        String actualString = dto.toString();

        // Then
        assertEquals(expectedString, actualString);
    }

    @Test
    public void equals_shouldReturnTrueForEqualObjects() {
        // Given
        ConnectionDTO dto1 = ConnectionDTO.builder()
                .userId(1L)
                .firstname("John")
                .lastname("Doe")
                .build();

        ConnectionDTO dto2 = ConnectionDTO.builder()
                .userId(1L)
                .firstname("John")
                .lastname("Doe")
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertTrue(result);
    }

    @Test
    public void equals_shouldReturnFalseForDifferentObjects() {
        // Given
        ConnectionDTO dto1 = ConnectionDTO.builder()
                .userId(1L)
                .firstname("John")
                .lastname("Doe")
                .build();

        ConnectionDTO dto2 = ConnectionDTO.builder()
                .userId(2L)
                .firstname("Jane")
                .lastname("Doe")
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertFalse(result);
    }
}
