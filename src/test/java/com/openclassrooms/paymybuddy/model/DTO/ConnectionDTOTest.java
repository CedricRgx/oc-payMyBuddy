package com.openclassrooms.paymybuddy.model.DTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
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
                .userId(2L)
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

    @Test
    public void hashCode_withNullFields_shouldHandleNullsGracefully() {
        // Given
        ConnectionDTO dtoWithNullFirstname = ConnectionDTO.builder()
                .userId(1L)
                .firstname(null)
                .lastname("Doe")
                .build();

        ConnectionDTO dtoWithNullLastname = ConnectionDTO.builder()
                .userId(1L)
                .firstname("John")
                .lastname(null)
                .build();

        // When Then
        assertDoesNotThrow(dtoWithNullFirstname::hashCode);
        assertDoesNotThrow(dtoWithNullLastname::hashCode);
    }

    @Test
    public void hashCode_consistencyCheck() {
        // Given
        ConnectionDTO dto = ConnectionDTO.builder()
                .userId(1L)
                .firstname("John")
                .lastname("Doe")
                .build();

        int initialHashCode = dto.hashCode();

        // When Then
        assertEquals(initialHashCode, dto.hashCode());
        assertEquals(initialHashCode, dto.hashCode());
    }

    @Test
    public void hashCode_eachFieldInfluence() {
        // Given
        ConnectionDTO baseDto = ConnectionDTO.builder()
                .userId(1L)
                .firstname("John")
                .lastname("Doe")
                .build();

        ConnectionDTO differentUserId = ConnectionDTO.builder()
                .userId(2L)
                .firstname("John")
                .lastname("Doe")
                .build();

        ConnectionDTO differentFirstname = ConnectionDTO.builder()
                .userId(1L)
                .firstname("Jane")
                .lastname("Doe")
                .build();

        ConnectionDTO differentLastname = ConnectionDTO.builder()
                .userId(1L)
                .firstname("John")
                .lastname("Smith")
                .build();

        // When Then
        assertNotEquals(baseDto.hashCode(), differentUserId.hashCode());
        assertNotEquals(baseDto.hashCode(), differentFirstname.hashCode());
        assertNotEquals(baseDto.hashCode(), differentLastname.hashCode());
    }

}
