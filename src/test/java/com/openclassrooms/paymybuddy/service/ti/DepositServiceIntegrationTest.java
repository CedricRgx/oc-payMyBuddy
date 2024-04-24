package com.openclassrooms.paymybuddy.service.ti;

import com.openclassrooms.paymybuddy.model.Deposit;
import com.openclassrooms.paymybuddy.repository.DepositRepository;
import com.openclassrooms.paymybuddy.service.impl.DepositService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class DepositServiceIntegrationTest {

    @Autowired
    private DepositService depositService;

    @Autowired
    private DepositRepository depositRepository;

    @BeforeEach
    void setup() {
        depositRepository.deleteAll();
    }

    @Test
    void testAddDeposit() {
        Deposit deposit = Deposit.builder().build();
        deposit.setAmount(100.00);
        deposit.setDescription("Test deposit");

        Deposit savedDeposit = depositService.addDeposit(deposit);
        assertNotNull(savedDeposit);
        assertNotNull(savedDeposit.getTransactionId());
        assertEquals(100.00, savedDeposit.getAmount());
    }

    @Test
    void testGetDepositById() {
        Deposit deposit = new Deposit();
        deposit.setAmount(100.00);
        deposit.setDescription("Test deposit");
        deposit = depositRepository.save(deposit);

        Optional<Deposit> foundDeposit = depositService.getDepositById(deposit.getTransactionId());
        assertTrue(foundDeposit.isPresent());
        assertEquals(deposit.getTransactionId(), foundDeposit.get().getTransactionId());
    }

    @Test
    void testGetAllDeposits() {
        Deposit deposit1 = new Deposit();
        deposit1.setAmount(100.00);
        deposit1.setDescription("Test deposit 1");
        Deposit deposit2 = new Deposit();
        deposit2.setAmount(200.00);
        deposit2.setDescription("Test deposit 2");
        depositRepository.save(deposit1);
        depositRepository.save(deposit2);

        Iterable<Deposit> deposits = depositService.getDeposits();
        assertNotNull(deposits);
        assertEquals(2, ((Collection<?>) deposits).size());
    }

    @Test
    void testDeleteDepositById() {
        Deposit deposit = new Deposit();
        deposit.setAmount(100.00);
        deposit.setDescription("Test deposit");
        deposit = depositRepository.save(deposit);

        depositService.deleteDepositById(deposit.getTransactionId());
        Optional<Deposit> deletedDeposit = depositRepository.findById(deposit.getTransactionId());
        assertFalse(deletedDeposit.isPresent());
    }
}
