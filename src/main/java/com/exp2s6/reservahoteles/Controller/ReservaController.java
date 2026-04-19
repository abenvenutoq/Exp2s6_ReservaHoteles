package com.exp2s6.reservahoteles.Controller;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.exp2s6.reservahoteles.Model.Reserva;
import com.exp2s6.reservahoteles.Service.ReservaService;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*")

public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;

    // Controlador para obtener todas las reservas
    @GetMapping 
    public List<Reserva> getAllReservas() {
        return reservaService.getAllReservas();
    }

    // Controlador para obtener una reserva por su ID
    @GetMapping("/{id}")
    public Optional<Reserva> getReservaById(@PathVariable Long id) {
        return reservaService.getReservaById(id);
    }

    // Controlador para crear una nueva reserva
    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        return reservaService.createReserva(reserva);

    }

    //Controlador para actualizar una reserva existente
    @PutMapping("/{id}")
    public Reserva updateReserva(@PathVariable Long id, @RequestBody Reserva reservaDetalles) {
        return reservaService.updateReserva(id, reservaDetalles)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: la ID informada no existe."));    
    }

    //Controlador para eliminar una reserva por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReserva(@PathVariable Long id) {
        boolean fueEliminado = reservaService.deleteReserva(id);
        
        if (!fueEliminado) {
            // Si el service devolvió false (no se encontró/no se eliminó), lanzamos el error
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: la ID informada no existe.");
        }
        // Si llegó hasta aquí, fueEliminado es true
        return ResponseEntity.ok("Reserva eliminada exitosamente.");
    }

}
