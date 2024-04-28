package com.openclassrooms.paymybuddy.model.DTO;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProfileDTOTest {

    @Autowired
    private Validator validator;

    private ProfileDTO dto = ProfileDTO.builder()
            .email("john.doe@example.com")
            .firstname("John")
            .lastname("Doe")
            .birthdate(LocalDate.of(2022, 5, 1))
            .address("123 Main St")
            .phone("123456789").build();

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
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
    public void testEmailGetter() {
        // Given
        String email = "john.doe@example.com";

        // When
        String retrievedEmail = dto.getEmail();

        // Then
        assertEquals(email, retrievedEmail);
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
    public void testFirstnameGetter() {
        // Given
        String firstname = "John";

        // When
        String retrievedFirstname = dto.getFirstname();

        // Then
        assertEquals(firstname, retrievedFirstname);
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
    public void testLastnameGetter() {
        // Given
        String lastname = "Doe";
        dto.setLastname(lastname);

        // When
        String retrievedLastname = dto.getLastname();

        // Then
        assertEquals(lastname, retrievedLastname);
    }

    @Test
    public void testBirthdateSetter() {
        // Given
        LocalDate birthdate = LocalDate.of(1999, 5, 1);

        // When
        dto.setBirthdate(birthdate);

        // Then
        assertEquals(birthdate, dto.getBirthdate());
    }

    @Test
    public void testBirthdateGetter() {
        // Given
        LocalDate birthdate = LocalDate.of(2022, 5, 1);
        dto.setBirthdate(birthdate);

        // When
        LocalDate retrievedBirthdate = dto.getBirthdate();

        // Then
        assertEquals(birthdate, retrievedBirthdate);
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
    public void testAddressGetter() {
        // Given
        String address = "123 Main St";
        dto.setAddress(address);

        // When
        String retrievedAddress = dto.getAddress();

        // Then
        assertEquals(address, retrievedAddress);
    }

    @Test
    public void testPhoneSetter() {
        // Given
        String phone = "987654321";

        // When
        dto.setPhone(phone);

        // Then
        assertEquals(phone, dto.getPhone());
    }

    @Test
    public void testPhoneGetter() {
        // Given
        String phone = "123456789";
        dto.setPhone(phone);

        // When
        String retrievedPhone = dto.getPhone();

        // Then
        assertEquals(phone, retrievedPhone);
    }

    @Test
    public void testEmailNotNull() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email(null)
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.now())
                .address("123 Main St")
                .phone("123456789")
                .build();

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "email".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testFirstnameNotNull() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname(null)
                .lastname("Doe")
                .birthdate(LocalDate.now())
                .address("123 Main St")
                .phone("123456789")
                .build();

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "firstname".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testLastnameNotNull() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname(null)
                .birthdate(LocalDate.now())
                .address("123 Main St")
                .phone("123456789")
                .build();

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "lastname".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testBirthdateNotNull() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(null)
                .address("123 Main St")
                .phone("123456789")
                .build();

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "birthdate".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testAddressNotNull() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.now())
                .address(null)
                .phone("123456789")
                .build();

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "address".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testPhoneNotNull() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.now())
                .address("123 Main St")
                .phone(null)
                .build();

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "phone".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void hashCode_shouldReturnSameHashCodeForSameObject() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When
        int hashCode1 = dto.hashCode();
        int hashCode2 = dto.hashCode();

        // Then
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void hashCode_shouldReturnSameValueForEqualObjects() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
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
    public void hashCode_shouldReturnDifferentValueForDifferentObjects() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("jane.doe@example.com")
                .firstname("Jane")
                .lastname("Doe")
                .birthdate(LocalDate.of(1995, 2, 2))
                .address("456 Second St")
                .phone("0987654321")
                .build();

        // When
        int hashCode1 = dto1.hashCode();
        int hashCode2 = dto2.hashCode();

        // Then
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    public void toString_shouldReturnExpectedString() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        String expected = "ProfileDTO(email=john.doe@example.com, firstname=John, lastname=Doe, birthdate=1990-01-01, address=123 Main St, phone=1234567890)";

        // When
        String actual = dto.toString();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void equals_withEqualObjects_shouldReturnTrue() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
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
    public void equals_withDifferentObjects_shouldReturnFalse() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("jane.doe@example.com")
                .firstname("Jane")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertFalse(result);
    }

    @Test
    public void equals_withNullObject_shouldReturnFalse() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When
        boolean result = dto.equals(null);

        // Then
        assertFalse(result);
    }

    @Test
    public void equals_withDifferentFirstnames_shouldReturnFalse() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("Jonathan")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When
        boolean result = dto1.equals(dto2);

        // Then
        assertFalse(result);
    }

    @Test
    public void hashCode_consistencyCheck_shouldReturnSameValue() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        int initialHashCode = dto.hashCode();

        // When
        int repeatedHashCode = dto.hashCode();

        // Then
        assertEquals(initialHashCode, repeatedHashCode);
    }

    @Test
    public void equals_reflexivityCheck_shouldReturnTrue() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertTrue(dto.equals(dto));
    }

    @Test
    public void equals_symmetryCheck_shouldBeSymmetric() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertTrue(dto1.equals(dto2) && dto2.equals(dto1));
    }

    @Test
    public void equals_transitivityCheck_shouldBeTransitive() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto3 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertTrue(dto1.equals(dto2) && dto2.equals(dto3) && dto1.equals(dto3));
    }

    @Test
    public void testEquals_DifferentClass() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        Object differentClassObject = new Object();

        // When Then
        assertFalse(dto.equals(differentClassObject));
    }

    @Test
    public void testEquals_Null() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertFalse(dto.equals(null));
    }

    @Test
    public void testHashCode_Reflexivity() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertTrue(dto.hashCode() == dto.hashCode());
    }



    @Test
    public void testEquals_DifferentEmails() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("jane.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_DifferentFirstname() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("Jane")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_DifferentLastname() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Shelley")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_DifferentBirthday() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1989, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_DifferentAddress() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("321 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_DifferentPhone() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("0234567890")
                .build();

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullEmail() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email(null)
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email(null)
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullEmail() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email(null)
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullFirstname() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname(null)
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname(null)
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullFirstname() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname(null)
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullLastname() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname(null)
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname(null)
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullLastname() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname(null)
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullBirthday() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(null)
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(null)
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullBirthday() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(null)
                .address("123 Main St")
                .phone("1234567890")
                .build();

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullAddress() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address(null)
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address(null)
                .phone("1234567890")
                .build();

        // When Then
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullAddress() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address(null)
                .phone("1234567890")
                .build();

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullPhone() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone(null)
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone(null)
                .build();

        // When Then
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullPhone() {
        // Given
        ProfileDTO dto1 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();

        ProfileDTO dto2 = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone(null)
                .build();

        // When Then
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testHashCode_MutationAffectsHashCode() {
        // Given
        ProfileDTO dto = ProfileDTO.builder()
                .email("john.doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthdate(LocalDate.of(1990, 1, 1))
                .address("123 Main St")
                .phone("1234567890")
                .build();
        int originalHashCode = dto.hashCode();

        // When
        dto.setEmail("changed@example.com");
        int mutatedHashCode = dto.hashCode();

        // Then
        assertNotEquals(originalHashCode, mutatedHashCode);
    }




}
