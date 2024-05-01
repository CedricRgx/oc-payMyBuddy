package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.DTO.NewTransfertDTO;
import com.openclassrooms.paymybuddy.model.DTO.TransfertDTO;
import com.openclassrooms.paymybuddy.model.Transfert;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TransfertServiceTest {

    @InjectMocks
    private TransfertService transfertService;

    @Mock
    private TransfertRepository transfertRepository;

    @Mock
    private UserService userService;

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
    public void testGetTransferts_shouldReturnAllTransferts() {
        // Given
        User user1 = User.builder().firstname("John").lastname("Doe").build();
        User user2 = User.builder().firstname("Mary").lastname("Shelley").build();
        Transfert transfert1 = Transfert.builder()
                .recipient(user1).build();
        Transfert transfert2 = Transfert.builder()
                .recipient(user2).build();

        // When
        List<Transfert> expectedTransferts = Arrays.asList(transfert1, transfert2);
        when(transfertRepository.findAll()).thenReturn(expectedTransferts);

        expectedTransferts = Arrays.asList(transfert1, transfert2);
        when(transfertRepository.findAll()).thenReturn(expectedTransferts);

        Iterable<Transfert> actualTransferts = transfertService.getTransferts();

        // Then
        assertThat(actualTransferts).containsExactlyInAnyOrderElementsOf(expectedTransferts);
        verify(transfertRepository).findAll();
    }

    @Test
    public void testGetTransfertById_shouldReturnTransfertWhenExists() {
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
    public void testGetTransfertById_shouldReturnEmptyWhenNotExists() {
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
    public void testAddTransfert_shouldAddTransfert() {
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
    public void testDeleteTransfertById_shouldDeleteTransfert() {
        // Given
        Long transfertId = 1L;
        doNothing().when(transfertRepository).deleteById(transfertId);

        // When
        transfertService.deleteTransfertById(transfertId);

        // Then
        verify(transfertRepository).deleteById(transfertId);
    }

    @Test
    public void testGetListOfTransferts_shouldReturnPagedTransferts() {
        // Given
        Long userId = 1L;
        int page = 0;
        int size = 10;
        User user = mock(User.class);
        lenient().when(user.getUserId()).thenReturn(userId);
        lenient().when(user.getFirstname()).thenReturn("John");
        lenient().when(user.getLastname()).thenReturn("Doe");

        User recipient1 = mock(User.class);
        when(recipient1.getFirstname()).thenReturn("John");
        when(recipient1.getLastname()).thenReturn("Doe");

        List<Transfert> transferts = new ArrayList<>();
        transferts.add(Transfert.builder()
                .recipient(recipient1)
                .author(user)
                .amount(100.0)
                .description("Test Transfer 1")
                .transactionDate(LocalDateTime.now()).build());

        lenient().when(transfertRepository.findAll()).thenReturn(transferts);

        // When
        Page<TransfertDTO> result = transfertService.getListOfTransferts(userId, page, size);

        // Then
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals("John", result.getContent().get(0).getRecipientFirstname());
        assertEquals("Doe", result.getContent().get(0).getRecipientLastname());
        assertEquals("100.00", result.getContent().get(0).getAmount());
        assertEquals("Test Transfer 1", result.getContent().get(0).getDescription());
    }

    @Test
    public void testAddNewTransfert_ShouldFail_WhenAmountIsInvalid() {
        // Given
        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder()
                .amount(-10.0).build();

        // When Then
        assertFalse(transfertService.addNewTransfert(newTransfertDTO));
    }

    @Test
    public void testAddNewTransfert_ShouldFail_WhenBalanceIsInsufficient() {
        // Given
        when(userService.getUserIdByEmail("user@example.com")).thenReturn(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));
        when(userService.getUserBalance(1L)).thenReturn(5.0);

        // When
        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder()
                .amount(10.0)
                .recipientId(2L).build();

        // Then
        assertFalse(transfertService.addNewTransfert(newTransfertDTO));
    }

    @Test
    public void testAddNewTransfert_ShouldFail_WhenUserNotFound() {
        // When
        when(userService.getUserIdByEmail("user@example.com")).thenReturn(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.empty());

        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder()
                .amount(50.0)
                .recipientId(2L).build();

        // Then
        assertFalse(transfertService.addNewTransfert(newTransfertDTO));
    }

    @Test
    public void testAddNewTransfert_ShouldSucceed_WhenValidRequest() {
        // When
        when(userService.getUserIdByEmail("user@example.com")).thenReturn(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));
        when(userService.getUserById(2L)).thenReturn(Optional.of(new User()));
        when(userService.getUserBalance(1L)).thenReturn(100.0);

        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder()
                .amount(50.0)
                .recipientId(2L).build();

        // Then
        assertTrue(transfertService.addNewTransfert(newTransfertDTO));
    }

    @Test
    public void testAddNewTransfert_ShouldFail_WhenRecipientNotFound() {
        // Given
        when(userService.getUserIdByEmail("user@example.com")).thenReturn(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));
        when(userService.getUserBalance(1L)).thenReturn(100.0);
        when(userService.getUserById(2L)).thenReturn(Optional.empty());

        // When
        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder()
                .amount(20.0)
                .recipientId(2L).build();

        // Then
        assertFalse(transfertService.addNewTransfert(newTransfertDTO));
    }

    @Test
    public void testAddNewTransfert_ShouldFail_WhenAmountIsNonPositive() {
        // Given
        when(userService.getUserIdByEmail("user@example.com")).thenReturn(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));
        when(userService.getUserBalance(1L)).thenReturn(100.0);

        // When

        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder()
                .amount(0)
                .recipientId(2L).build();

        // Then
        assertFalse(transfertService.addNewTransfert(newTransfertDTO));
    }

    @Test
    public void testAddNewTransfert_ShouldFail_WhenBalanceIsNull() {
        // Given
        when(userService.getUserIdByEmail("user@example.com")).thenReturn(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));
        when(userService.getUserBalance(1L)).thenReturn(null);

        // When
        NewTransfertDTO newTransfertDTO = NewTransfertDTO.builder()
                .amount(50.0)
                .recipientId(2L).build();

        // Then
        assertFalse(transfertService.addNewTransfert(newTransfertDTO));
    }

}