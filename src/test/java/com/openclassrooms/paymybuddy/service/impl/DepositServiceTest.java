package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.Deposit;
import com.openclassrooms.paymybuddy.repository.DepositRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DepositServiceTest {

    @Mock
    private DepositRepository depositRepository;

    @InjectMocks
    private DepositService depositService;

    @Test
    public void testGetDeposits() {
        // When
        depositService.getDeposits();

        // Then
        verify(depositRepository).findAll();
    }

    @Test
    public void testGetDepositByIdFound() {
        // Given
        Long depositId = 1L;

        // When
        when(depositRepository.findById(depositId)).thenReturn(Optional.of(new Deposit()));
        depositService.getDepositById(depositId);

        // Then
        verify(depositRepository).findById(depositId);
    }

    @Test
    public void testGetDepositByIdNotFound() {
        // Given
        Long depositId = 1L;

        // When
        when(depositRepository.findById(depositId)).thenReturn(Optional.empty());
        depositService.getDepositById(depositId);

        // Then
        verify(depositRepository).findById(depositId);
    }

    @Test
    public void testAddDeposit() {
        // Given
        Deposit deposit = Deposit.builder().build();

        // When
        depositService.addDeposit(deposit);

        // Then
        verify(depositRepository).save(deposit);
    }

    @Test
    public void testDeleteDepositById() {
        // Given
        Long depositId = 1L;

        // When
        depositService.deleteDepositById(depositId);

        // Then
        verify(depositRepository).deleteById(depositId);
    }

}
