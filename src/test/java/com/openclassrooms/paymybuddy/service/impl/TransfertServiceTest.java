package com.openclassrooms.paymybuddy.service.impl;

import com.openclassrooms.paymybuddy.model.Transfert;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import com.openclassrooms.paymybuddy.service.impl.TransfertService;
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
public class TransfertServiceTest {

    @Mock
    private TransfertRepository transfertRepository;

    @InjectMocks
    private TransfertService transfertService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTransferts() {
        log.info("Running testGetTransferts() test in TransfertServiceTest class");
        transfertService.getTransferts();
        verify(transfertRepository).findAll();
    }

    @Test
    public void testGetTransfertByIdFound() {
        log.info("Running testGetTransfertByIdFound() test in TransfertServiceTest class");
        Long transfertId = 1L;
        when(transfertRepository.findById(transfertId)).thenReturn(Optional.of(new Transfert()));
        transfertService.getTransfertsById(transfertId);
        verify(transfertRepository).findById(transfertId);
    }

    @Test
    public void testGetTransfertByIdNotFound() {
        log.info("Running testGetTransfertByIdNotFound() test in TransfertServiceTest class");
        Long transfertId = 1L;
        when(transfertRepository.findById(transfertId)).thenReturn(Optional.empty());
        transfertService.getTransfertsById(transfertId);
        verify(transfertRepository).findById(transfertId);
    }

    @Test
    public void testAddTransfert() {
        log.info("Running testAddTransfert() test in TransfertServiceTest class");
        Transfert transfert = new Transfert();
        transfertService.addTransfert(transfert);
        verify(transfertRepository).save(transfert);
    }

    @Test
    public void testDeleteTransfertById() {
        log.info("Running testDeleteTransfertById() test in TransfertServiceTest class");
        Long transfertId = 1L;
        transfertService.deleteTransfertById(transfertId);
        verify(transfertRepository).deleteById(transfertId);
    }
}
