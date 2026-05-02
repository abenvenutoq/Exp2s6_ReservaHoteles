package com.exp2s6.reservahoteles.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ReservaTest {

    @Test
    void testGetterAndSetter(){
        Reserva reservas = new Reserva();
        reservas.setId(1L);
        reservas.setFechaReserva("02-05-2026");
        reservas.setNombreCliente("Angelo Benvenuto");
        reservas.setNumeroHabitacion(202);
        reservas.setFechaIngreso("05-05-2026");
        reservas.setFechaSalida("10-05-2026");

        assertEquals(1L, reservas.getId());
        assertEquals("02-05-2026", reservas.getFechaReserva());
        assertEquals("Angelo Benvenuto", reservas.getNombreCliente());
        assertEquals(202, reservas.getNumeroHabitacion());
        assertEquals("05-05-2026", reservas.getFechaIngreso());
        assertEquals("10-05-2026", reservas.getFechaSalida());

    }
    
}
