package com.openclassrooms.paymybuddy.model.DTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
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

    @Test
    public void hashCode_shouldReturnEqualValuesForEqualObjects() {
        // Given
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When
        int hashCode1 = dto1.hashCode();
        int hashCode2 = dto2.hashCode();

        // Then
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void hashCode_shouldReturnDifferentValuesForDifferentObjects() {
        // Given
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        RegisterDTO dto2 = RegisterDTO.builder()
                .email("jane.smith@example.com")
                .password("password456")
                .firstname("Jane")
                .lastname("Smith")
                .birthdate(LocalDate.of(1995, 5, 5))
                .address("456 Elm St")
                .phone("9876543210")
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
        RegisterDTO dto = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        String expectedString = "RegisterDTO(email=john.doe@example.com, password=password123, " +
                "firstname=John, lastname=Doe, birthdate=1990-01-01, address=123 Main St, phone=1234567890)";

        // When
        String actualString = dto.toString();

        // Then
        assertEquals(expectedString, actualString);
    }

    @Test
    public void equals_shouldReturnTrueWhenObjectsAreEqual() {
        // Given
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertTrue(result);
    }

    @Test
    public void equals_shouldReturnFalseWhenObjectsAreNotEqual() {
        // Given
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("jane.doe@example.com")
                .password("password123")
                .firstname("Jane")
                .lastname("Doe")
                .birthdate(LocalDate.of(1995, 2, 2))
                .address("456 Second St")
                .phone("0987654321")
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertFalse(result);
    }

    @Test
    public void testEquals_BothObjectsHaveNullEmail() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email(null)
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email(null)
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullEmail() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email(null)
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullPassword() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password(null)
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password(null)
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullPassword() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password(null)
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullFirstname() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname(null)
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname(null)
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullFirstname() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname(null)
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullLastname() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname(null)
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname(null)
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullLastname() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname(null)
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullBirthdate() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(null)
                .address("123 Main St")
                .phone("1234567890")
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(null)
                .address("123 Main St")
                .phone("1234567890")
                .build();
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullBirthdate() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(null)
                .address("123 Main St")
                .phone("1234567890")
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullAddress() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address(null)
                .phone("1234567890")
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address(null)
                .phone("1234567890")
                .build();
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullAddress() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address(null)
                .phone("1234567890")
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullPhone() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone(null)
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone(null)
                .build();
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullPhone() {
        RegisterDTO dto1 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone(null)
                .build();
        RegisterDTO dto2 = RegisterDTO.builder()
                .email("john.doe@example.com")
                .password("password123")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        assertFalse(dto1.equals(dto2));
    }

}
