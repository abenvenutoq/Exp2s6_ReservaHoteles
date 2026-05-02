package com.exp2s6.reservahoteles.Controller;

import com.exp2s6.reservahoteles.Model.Reserva;
import com.exp2s6.reservahoteles.Service.ReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReservaService reservaService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Reserva reservaMock;

    @BeforeEach
    void setUp(){
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
    void testGetAllReservas() throws Exception{
        when(reservaService.getAllReservas()).thenReturn(Arrays.asList(reservaMock));

        mockMvc.perform(get("/reservas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].fechaReserva").value("02-05-2026"))
                .andExpect(jsonPath("$[0].nombreCliente").value("Angelo Benvenuto"))
                .andExpect(jsonPath("$[0].numeroHabitacion").value(202))
                .andExpect(jsonPath("$[0].fechaIngreso").value("05-05-2026"))
                .andExpect(jsonPath("$[0].fechaSalida").value("10-05-2026"))
                ;
    }

    @Test
    void testGetReservaById() throws Exception {

        when(reservaService.getReservaById(1L)).thenReturn(Optional.of(reservaMock));

        mockMvc.perform(get("/reservas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fechaReserva").value("02-05-2026"))
                .andExpect(jsonPath("$.nombreCliente").value("Angelo Benvenuto"))
                .andExpect(jsonPath("$.numeroHabitacion").value(202))
                .andExpect(jsonPath("$.fechaIngreso").value("05-05-2026"))
                .andExpect(jsonPath("$.fechaSalida").value("10-05-2026"))
                ;
    }

    @Test
    void testCreateReserva() throws Exception{
        when(reservaService.createReserva(any(Reserva.class))).thenReturn(reservaMock);

        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservaMock)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.fechaReserva").value("02-05-2026"))
                .andExpect(jsonPath("$.nombreCliente").value("Angelo Benvenuto"))
                .andExpect(jsonPath("$.numeroHabitacion").value(202))
                .andExpect(jsonPath("$.fechaIngreso").value("05-05-2026"))
                .andExpect(jsonPath("$.fechaSalida").value("10-05-2026"));
    }

    @Test
    void testUpdateReserva() throws Exception{
        when(reservaService.updateReserva(eq(1L), any(Reserva.class))).thenReturn(Optional.of(reservaMock));

        mockMvc.perform(put("/reservas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reservaMock)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fechaReserva").value("02-05-2026"))
                .andExpect(jsonPath("$.nombreCliente").value("Angelo Benvenuto"))
                .andExpect(jsonPath("$.numeroHabitacion").value(202))
                .andExpect(jsonPath("$.fechaIngreso").value("05-05-2026"))
                .andExpect(jsonPath("$.fechaSalida").value("10-05-2026"));
    }

    @Test
    void testDeleteReserva() throws Exception{
        when(reservaService.deleteReserva(1L)).thenReturn(true);

        mockMvc.perform(delete("/reservas/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Reserva eliminada exitosamente."));
    }

}
