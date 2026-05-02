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

    // --- Tests para getAllReservas ---

    @Test
    void testGetAllReservas() {
        // Given
        when(reservaRepository.findAll()).thenReturn(Arrays.asList(reservaMock));

        // When
        List<Reserva> resultado = reservaService.getAllReservas();

        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Angelo Benvenuto", resultado.get(0).getNombreCliente());
        
        // Verifica que el repositorio fue llamado exactamente una vez
        verify(reservaRepository, times(1)).findAll();
    }

    // --- Tests para getReservaById ---

    @Test
    void testGetReservaById() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reservaMock));

        Optional<Reserva> resultado = reservaService.getReservaById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertEquals(202, resultado.get().getNumeroHabitacion());
    }

    // --- Tests para createReserva ---

    @Test
    void testCreateReserva() {
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaMock);

        Reserva resultado = reservaService.createReserva(reservaMock);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Angelo Benvenuto", resultado.getNombreCliente());
    }

    // --- Tests para updateReserva ---

    @Test
    void testUpdateReserva_Exitosa() {
        // Simulamos que la ID sí existe en la base de datos
        when(reservaRepository.existsById(1L)).thenReturn(true);
        // Simulamos el guardado
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaMock);

        Optional<Reserva> resultado = reservaService.updateReserva(1L, reservaMock);

        assertTrue(resultado.isPresent());
        assertEquals("Angelo Benvenuto", resultado.get().getNombreCliente());
        
        verify(reservaRepository).existsById(1L);
        verify(reservaRepository).save(reservaMock);
    }

    @Test
    void testUpdateReserva_NoEncontrada() {
        // Simulamos que la ID NO existe
        when(reservaRepository.existsById(1L)).thenReturn(false);

        Optional<Reserva> resultado = reservaService.updateReserva(1L, reservaMock);

        assertFalse(resultado.isPresent()); // Debe devolver un Optional.empty()
        
        // Verificamos que al no existir, NUNCA se llame al método save
        verify(reservaRepository, never()).save(any(Reserva.class));
    }

    // --- Tests para deleteReserva ---

    @Test
    void testDeleteReserva_Exitosa() {
        // Simulamos que la ID sí existe
        when(reservaRepository.existsById(1L)).thenReturn(true);
        // No necesitamos simular deleteById porque es un método void y Mockito por defecto no hace nada

        boolean resultado = reservaService.deleteReserva(1L);

        assertTrue(resultado);
        
        // Verificamos que se haya ejecutado la comprobación y luego la eliminación
        verify(reservaRepository).existsById(1L);
        verify(reservaRepository).deleteById(1L);
    }

    @Test
    void testDeleteReserva_NoEncontrada() {
        // Simulamos que la ID NO existe
        when(reservaRepository.existsById(1L)).thenReturn(false);

        boolean resultado = reservaService.deleteReserva(1L);

        assertFalse(resultado);
        
        // Verificamos que al no existir, NUNCA se intente eliminar
        verify(reservaRepository, never()).deleteById(anyLong());
    }
}
