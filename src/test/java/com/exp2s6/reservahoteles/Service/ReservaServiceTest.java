package com.exp2s6.reservahoteles.Service;

import com.exp2s6.reservahoteles.Model.Reserva;
import com.exp2s6.reservahoteles.Repository.ReservaRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaServiceImpl reservaService;

    private Reserva reservaMock;

    @BeforeEach
    void setUp() {
        reservaMock = new Reserva();
        reservaMock.setId(1L);
        reservaMock.setFechaReserva("02-05-2026");
        reservaMock.setNombreCliente("Angelo Benvenuto");
        reservaMock.setNumeroHabitacion(202);
        reservaMock.setFechaIngreso("05-05-2026");
        reservaMock.setFechaSalida("10-05-2026");

    }

    @AfterEach
    void tearDown() {
        reservaMock = null;
    }

    @Test
    void testGetAllReservas() {
        when(reservaRepository.findAll()).thenReturn(Arrays.asList(reservaMock));

        List<Reserva> resultado = reservaService.getAllReservas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Angelo Benvenuto", resultado.get(0).getNombreCliente());
        
        verify(reservaRepository, times(1)).findAll();
    }

    @Test
    void testGetReservaById() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reservaMock));

        Optional<Reserva> resultado = reservaService.getReservaById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertEquals(202, resultado.get().getNumeroHabitacion());
    }

    @Test
    void testCreateReserva() {
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaMock);

        Reserva resultado = reservaService.createReserva(reservaMock);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Angelo Benvenuto", resultado.getNombreCliente());
    }

    @Test
    void testUpdateReserva_Exitosa() {
        when(reservaRepository.existsById(1L)).thenReturn(true);

        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaMock);

        Optional<Reserva> resultado = reservaService.updateReserva(1L, reservaMock);

        assertTrue(resultado.isPresent());
        assertEquals("Angelo Benvenuto", resultado.get().getNombreCliente());
        
        verify(reservaRepository).existsById(1L);
        verify(reservaRepository).save(reservaMock);
    }

    @Test
    void testUpdateReserva_NoEncontrada() {
        when(reservaRepository.existsById(1L)).thenReturn(false);

        Optional<Reserva> resultado = reservaService.updateReserva(1L, reservaMock);

        assertFalse(resultado.isPresent());
        
        verify(reservaRepository, never()).save(any(Reserva.class));
    }

    @Test
    void testDeleteReserva_Exitosa() {
        when(reservaRepository.existsById(1L)).thenReturn(true);

        boolean resultado = reservaService.deleteReserva(1L);

        assertTrue(resultado);

        verify(reservaRepository).existsById(1L);
        verify(reservaRepository).deleteById(1L);
    }

    @Test
    void testDeleteReserva_NoEncontrada() {
        when(reservaRepository.existsById(1L)).thenReturn(false);

        boolean resultado = reservaService.deleteReserva(1L);

        assertFalse(resultado);
        
        verify(reservaRepository, never()).deleteById(anyLong());
    }
}
