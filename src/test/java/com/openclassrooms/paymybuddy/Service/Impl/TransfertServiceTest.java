package com.openclassrooms.paymybuddy.Service.Impl;

import com.openclassrooms.paymybuddy.model.Transfert;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import com.openclassrooms.paymybuddy.service.impl.TransfertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        transfertService.getTransferts();
        verify(transfertRepository).findAll();
    }

    @Test
    public void testGetTransfertByIdFound() {
        Long transfertId = 1L;
        when(transfertRepository.findById(transfertId)).thenReturn(Optional.of(new Transfert()));
        transfertService.getTransfertsById(transfertId);
        verify(transfertRepository).findById(transfertId);
    }

    @Test
    public void testGetTransfertByIdNotFound() {
        Long transfertId = 1L;
        when(transfertRepository.findById(transfertId)).thenReturn(Optional.empty());
        transfertService.getTransfertsById(transfertId);
        verify(transfertRepository).findById(transfertId);
    }

    @Test
    public void testAddTransfert() {
        Transfert transfert = new Transfert();
        transfertService.addTransfert(transfert);
        verify(transfertRepository).save(transfert);
    }

    @Test
    public void testDeleteTransfertById() {
        Long transfertId = 1L;
        transfertService.deleteTransfertById(transfertId);
        verify(transfertRepository).deleteById(transfertId);
    }
}
