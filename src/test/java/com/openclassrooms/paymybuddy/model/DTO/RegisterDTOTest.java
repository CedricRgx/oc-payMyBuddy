package com.openclassrooms.paymybuddy.model.DTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * unit tests for the RegisterDTO class.
 */
@Slf4j
public class RegisterDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private RegisterDTO dto = RegisterDTO.builder()
            .email("john.doe@example.com")
            .password("password123")
            .firstname("John")
            .lastname("Doe")
            .birthdate(LocalDate.of(1990, 1, 1))
            .address("123 Main St")
            .phone("123456789").build();

    @Test
    public void testEmailGetter() {
        // Given
        String email = "john.doe@example.com";

        // When
        String retrievedEmail = dto.getEmail();

        // Then
        assertEquals(email, retrievedEmail);
    }

    @Test
    public void testEmailSetter() {
        // Given
        String email = "mary.shelley@example.com";

        // When
        dto.setEmail(email);

        // Then
        assertEquals(email, dto.getEmail());
    }

    @Test
    public void testPasswordGetter() {
        // Given
        String password = "password123";

        // When
        String retrievedPassword = dto.getPassword();

        // Then
        assertEquals(password, retrievedPassword);
    }

    @Test
    public void testPasswordSetter() {
        // Given
        String password = "password321";

        // When
        dto.setPassword(password);

        // Then
        assertEquals(password, dto.getPassword());
    }

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
    public void testBirthdateGetter() {
        // Given
        LocalDate birthdate = LocalDate.of(1990, 1, 1);

        // When
        LocalDate retrievedBirthdate = dto.getBirthdate();

        // Then
        assertEquals(birthdate, retrievedBirthdate);
    }

    @Test
    public void testBirthdateSetter() {
        // Given
        LocalDate birthdate = LocalDate.of(1985, 1, 1);

        // When
        dto.setBirthdate(birthdate);

        // Then
        assertEquals(birthdate, dto.getBirthdate());
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
        String phone = "123456789";

        // When
        String retrievedPhone = dto.getPhone();

        // Then
        assertEquals(phone, retrievedPhone);
    }

    @Test
    public void testPhoneSetter() {
        // Given
        String phone = "0987654321";

        // When
        dto.setPhone(phone);

        // Then
        assertEquals(phone, dto.getPhone());
    }

    @Test
    public void testValidDTO() {
        // Given
        RegisterDTO dto = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(dto);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidEmail() {
        // Given
        RegisterDTO dto = RegisterDTO.builder()
                .email("john.doeexample.com") // Invalid email format
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("email", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidPassword() {
        // Given
        RegisterDTO dto = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("pass")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(dto);

        // Then
        assertTrue(violations.isEmpty());
        assertEquals(0, violations.size());
    }

    @Test
    public void testInvalidFirstname() {
        // Given
        RegisterDTO dto = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("") // Invalid firstname length
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
        assertEquals("firstname", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidLastname() {
        // Given
        RegisterDTO dto = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("") // Invalid lastname length
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
        assertEquals("lastname", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidBirthdate() {
        // Given
        RegisterDTO dto = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.now().plusDays(1)) // Invalid future birthdate
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("birthdate", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidAddress() {
        // Given
        RegisterDTO dto = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("") // Invalid address length
                .phone("1234567890")
                .build();

        // When
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
        assertEquals("address", violations.iterator().next().getPropertyPath().toString());
    }

    @Test
    public void testInvalidPhone() {
        // Given
        RegisterDTO dto = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("not a number") // Invalid phone format
                .build();

        // When
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(dto);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("phone", violations.iterator().next().getPropertyPath().toString());
    }


}
