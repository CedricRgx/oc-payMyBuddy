package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.Deposit;
import com.openclassrooms.paymybuddy.repository.DepositRepository;
import com.openclassrooms.paymybuddy.service.impl.DepositService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepositServiceTest {

    @Mock
    private DepositRepository depositRepository;

    @InjectMocks
    private DepositService depositService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDeposits() {
        depositService.getDeposits();
        verify(depositRepository).findAll();
    }

    @Test
    public void testGetDepositByIdFound() {
        Long depositId = 1L;
        when(depositRepository.findById(depositId)).thenReturn(Optional.of(new Deposit()));
        depositService.getDepositById(depositId);
        verify(depositRepository).findById(depositId);
    }

    @Test
    public void testGetDepositByIdNotFound() {
        Long depositId = 1L;
        when(depositRepository.findById(depositId)).thenReturn(Optional.empty());
        depositService.getDepositById(depositId);
        verify(depositRepository).findById(depositId);
    }

    @Test
    public void testAddDeposit() {
        Deposit deposit = new Deposit();
        depositService.addDeposit(deposit);
        verify(depositRepository).save(deposit);
    }

    @Test
    public void testDeleteDepositById() {
        Long depositId = 1L;
        depositService.deleteDepositById(depositId);
        verify(depositRepository).deleteById(depositId);
    }

}
