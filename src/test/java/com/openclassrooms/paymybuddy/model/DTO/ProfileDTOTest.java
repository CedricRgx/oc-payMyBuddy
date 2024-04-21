package com.openclassrooms.paymybuddy.model.DTO;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProfileDTOTest {

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
        ProfileDTO dto = new ProfileDTO(null, "John", "Doe", LocalDate.now(), "123 Main St", "123456789");

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "email".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testFirstnameNotNull() {
        // Given
        ProfileDTO dto = new ProfileDTO("john.doe@example.com", null, "Doe", LocalDate.now(), "123 Main St", "123456789");

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "firstname".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testLastnameNotNull() {
        // Given
        ProfileDTO dto = new ProfileDTO("john.doe@example.com", "John", null, LocalDate.now(), "123 Main St", "123456789");

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "lastname".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testBirthdateNotNull() {
        // Given
        ProfileDTO dto = new ProfileDTO("john.doe@example.com", "John", "Doe", null, "123 Main St", "123456789");

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "birthdate".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testAddressNotNull() {
        // Given
        ProfileDTO dto = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.now(), null, "123456789");

        // When
        var violations = validator.validate(dto);

        // Then
        assertTrue(violations.stream().anyMatch(v -> "address".equals(v.getPropertyPath().toString())));
    }

    @Test
    public void testPhoneNotNull() {
        // Given
        ProfileDTO dto = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.now(), "123 Main St", null);

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
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO("jane.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_DifferentFirstname() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "Jane", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_DifferentLastname() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", "Shelley", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_DifferentBirthday() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1989, 1, 1), "123 Main St", "1234567890");
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_DifferentAddress() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "321 Main St", "1234567890");
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_DifferentPhone() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "0234567890");
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullEmail() {
        ProfileDTO dto1 = new ProfileDTO(null, "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO(null, "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullEmail() {
        ProfileDTO dto1 = new ProfileDTO(null, "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullFirstname() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", null, "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", null, "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullFirstname() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", null, "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullLastname() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", null, LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", null, LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullLastname() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", null, LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullBirthday() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", "Doe", null, "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", "Doe", null, "123 Main St", "1234567890");
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullBirthday() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", "Doe", null, "123 Main St", "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullAddress() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), null, "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), null, "1234567890");
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullAddress() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), null, "1234567890");
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquals_BothObjectsHaveNullPhone() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", null);
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", null);
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquals_NullAndNonNullPhone() {
        ProfileDTO dto1 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", null);
        ProfileDTO dto2 = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testHashCode_MutationAffectsHashCode() {
        ProfileDTO dto = new ProfileDTO("john.doe@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890");
        int originalHashCode = dto.hashCode();
        dto.setEmail("changed@example.com");
        int mutatedHashCode = dto.hashCode();
        assertNotEquals(originalHashCode, mutatedHashCode);
    }




}
