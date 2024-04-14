package com.openclassrooms.paymybuddy.model.DTO;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * unit tests for the ConnectionDTO class.
 */
@Slf4j
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
}
