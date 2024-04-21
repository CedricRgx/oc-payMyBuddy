package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.DTO.NewTransfertDTO;
import com.openclassrooms.paymybuddy.model.DTO.TransfertDTO;
import com.openclassrooms.paymybuddy.model.Transfert;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import com.openclassrooms.paymybuddy.service.impl.TransfertService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransfertServiceTest {

    @InjectMocks
    private TransfertService transfertService;

    @Mock
    private TransfertRepository transfertRepository;

    @Mock
    private UserService userService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    SecurityContext securityContext;

    @BeforeEach
    public void setUp() {
        Authentication authentication = mock(Authentication.class);
        lenient().when(authentication.getName()).thenReturn("user@example.com");
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }


    @Test
    public void getTransferts_shouldReturnAllTransferts() {
        // Given
        User user1 = User.builder().firstname("John").lastname("Doe").build();
        User user2 = User.builder().firstname("Mary").lastname("Shelley").build();

        Transfert transfert1 = new Transfert();
        transfert1.setRecipient(user1);

        Transfert transfert2 = new Transfert();
        transfert2.setRecipient(user2);

        List<Transfert> expectedTransferts = Arrays.asList(transfert1, transfert2);
        when(transfertRepository.findAll()).thenReturn(expectedTransferts);

        expectedTransferts = Arrays.asList(transfert1, transfert2);
        when(transfertRepository.findAll()).thenReturn(expectedTransferts);

        // When
        Iterable<Transfert> actualTransferts = transfertService.getTransferts();

        // Then
        assertThat(actualTransferts).containsExactlyInAnyOrderElementsOf(expectedTransferts);
        verify(transfertRepository).findAll();
    }

    @Test
    public void getTransfertById_shouldReturnTransfertWhenExists() {
        // Given
        Long transfertId = 1L;
        Optional<Transfert> expectedTransfert = Optional.of(new Transfert());
        when(transfertRepository.findById(transfertId)).thenReturn(expectedTransfert);

        // When
        Optional<Transfert> actualTransfert = transfertService.getTransfertById(transfertId);

        // Then
        assertTrue(actualTransfert.isPresent());
        assertEquals(expectedTransfert, actualTransfert);
        verify(transfertRepository).findById(transfertId);
    }

    @Test
    public void getTransfertById_shouldReturnEmptyWhenNotExists() {
        // Given
        Long transfertId = 1L;
        when(transfertRepository.findById(transfertId)).thenReturn(Optional.empty());

        // When
        Optional<Transfert> actualTransfert = transfertService.getTransfertById(transfertId);

        // Then
        assertFalse(actualTransfert.isPresent());
        verify(transfertRepository).findById(transfertId);
    }

    @Test
    public void addTransfert_shouldAddTransfert() {
        // Given
        Transfert transfert = new Transfert();
        when(transfertRepository.save(transfert)).thenReturn(transfert);

        // When
        Transfert savedTransfert = transfertService.addTransfert(transfert);

        // Then
        assertNotNull(savedTransfert);
        verify(transfertRepository).save(transfert);
    }

    @Test
    public void deleteTransfertById_shouldDeleteTransfert() {
        // Given
        Long transfertId = 1L;
        doNothing().when(transfertRepository).deleteById(transfertId);

        // When
        transfertService.deleteTransfertById(transfertId);

        // Then
        verify(transfertRepository).deleteById(transfertId);
    }

    @Test
    public void getListOfConnections_shouldReturnConnections() {
        // Given
        Long userId = 1L;
        List<String> expectedConnections = Arrays.asList("John Doe", "Jane Doe");
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(expectedConnections);

        // When
        List<String> actualConnections = transfertService.getListOfConnections(userId);

        // Then
        assertEquals(expectedConnections, actualConnections);
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(RowMapper.class));
    }

    @Test
    public void getListOfTransferts_shouldReturnPagedTransferts() {
        // Given
        Long userId = 1L;
        int page = 0, size = 10;
        List<TransfertDTO> expectedTransferts = Arrays.asList(TransfertDTO.builder().build());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(expectedTransferts);

        // When
        List<TransfertDTO> actualTransferts = transfertService.getListOfTransferts(userId, page, size);

        // Then
        assertEquals(expectedTransferts, actualTransferts);
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(RowMapper.class));
    }

    @Test
    public void countTransferts_shouldReturnCount() {
        // Given
        Long userId = 1L;
        int expectedCount = 5;
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Integer.class))).thenReturn(expectedCount);

        // When
        int actualCount = transfertService.countTransferts(userId);

        // Then
        assertEquals(expectedCount, actualCount);
        verify(jdbcTemplate).queryForObject(anyString(), any(Object[].class), eq(Integer.class));
    }

    @Test
    public void addNewTransfert_ShouldFail_WhenAmountIsInvalid() {
        // Given
        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder().build();
        newTransfertDTO.setAmount(-10.0);

        // When Then
        assertFalse(transfertService.addNewTransfert(newTransfertDTO));
    }

    @Test
    public void addNewTransfert_ShouldFail_WhenBalanceIsInsufficient() {
        // Given
        when(userService.getUserIdByEmail("user@example.com")).thenReturn(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));
        when(userService.getUserBalance(1L)).thenReturn(5.0);

        // When
        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder().build();
        newTransfertDTO.setAmount(10.0);
        newTransfertDTO.setRecipientId(2L);

        // Then
        assertFalse(transfertService.addNewTransfert(newTransfertDTO));
    }

    @Test
    public void addNewTransfert_ShouldFail_WhenUserNotFound() {
        // When
        when(userService.getUserIdByEmail("user@example.com")).thenReturn(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.empty()); // User not found

        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder().build();
        newTransfertDTO.setAmount(50.0);
        newTransfertDTO.setRecipientId(2L);

        // Then
        assertFalse(transfertService.addNewTransfert(newTransfertDTO));
    }

    @Test
    public void addNewTransfert_ShouldSucceed_WhenValidRequest() {
        // When
        when(userService.getUserIdByEmail("user@example.com")).thenReturn(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));
        when(userService.getUserById(2L)).thenReturn(Optional.of(new User()));
        when(userService.getUserBalance(1L)).thenReturn(100.0);

        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder().build();
        newTransfertDTO.setAmount(50.0);
        newTransfertDTO.setRecipientId(2L);

        // Then
        assertTrue(transfertService.addNewTransfert(newTransfertDTO));
    }

    @Test
    public void addNewTransfert_ShouldFail_WhenRecipientNotFound() {
        // Given
        when(userService.getUserIdByEmail("user@example.com")).thenReturn(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));
        when(userService.getUserBalance(1L)).thenReturn(100.0);
        when(userService.getUserById(2L)).thenReturn(Optional.empty());

        // When
        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder().build();
        newTransfertDTO.setAmount(20.0);
        newTransfertDTO.setRecipientId(2L);

        // Then
        assertFalse(transfertService.addNewTransfert(newTransfertDTO), "Transfer should fail if the recipient user is not found");
    }

    @Test
    public void addNewTransfert_ShouldFail_WhenAmountIsNonPositive() {
        // Given
        when(userService.getUserIdByEmail("user@example.com")).thenReturn(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));
        when(userService.getUserBalance(1L)).thenReturn(100.0);

        // When
        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder().build();
        newTransfertDTO.setAmount(0);
        newTransfertDTO.setRecipientId(2L);

        // Then
        assertFalse(transfertService.addNewTransfert(newTransfertDTO), "Transfer should fail if the amount is non-positive");
    }

    @Test
    public void addNewTransfert_ShouldFail_WhenBalanceIsNull() {
        // Given
        when(userService.getUserIdByEmail("user@example.com")).thenReturn(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));
        when(userService.getUserBalance(1L)).thenReturn(null);

        // When
        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder().build();
        newTransfertDTO.setAmount(50.0);
        newTransfertDTO.setRecipientId(2L);

        // Then
        assertFalse(transfertService.addNewTransfert(newTransfertDTO), "Transfer should fail if the balance is null");
    }

}