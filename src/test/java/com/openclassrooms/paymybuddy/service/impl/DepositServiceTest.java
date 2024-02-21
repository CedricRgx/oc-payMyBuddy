package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.Deposit;
import com.openclassrooms.paymybuddy.repository.DepositRepository;
import com.openclassrooms.paymybuddy.service.impl.DepositService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
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
        log.info("Running testGetDeposits() test in DepositServiceTest class");
        depositService.getDeposits();
        verify(depositRepository).findAll();
    }

    @Test
    public void testGetDepositByIdFound() {
        log.info("Running testGetDepositByIdFound() test in DepositServiceTest class");
        Long depositId = 1L;
        when(depositRepository.findById(depositId)).thenReturn(Optional.of(new Deposit()));
        depositService.getDepositById(depositId);
        verify(depositRepository).findById(depositId);
    }

    @Test
    public void testGetDepositByIdNotFound() {
        log.info("Running testGetDepositByIdNotFound() test in DepositServiceTest class");
        Long depositId = 1L;
        when(depositRepository.findById(depositId)).thenReturn(Optional.empty());
        depositService.getDepositById(depositId);
        verify(depositRepository).findById(depositId);
    }

    @Test
    public void testAddDeposit() {
        log.info("Running testAddDeposit() test in DepositServiceTest class");
        Deposit deposit = new Deposit();
        depositService.addDeposit(deposit);
        verify(depositRepository).save(deposit);
    }

    @Test
    public void testDeleteDepositById() {
        log.info("Running testDeleteDepositById() test in DepositServiceTest class");
        Long depositId = 1L;
        depositService.deleteDepositById(depositId);
        verify(depositRepository).deleteById(depositId);
    }

}
