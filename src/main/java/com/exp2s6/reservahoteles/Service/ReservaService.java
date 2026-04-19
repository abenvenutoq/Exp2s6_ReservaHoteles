package com.exp2s6.reservahoteles.Service;

import com.exp2s6.reservahoteles.Model.Reserva;
import java.util.List;
import java.util.Optional;

public interface ReservaService {

    List<Reserva> getAllReservas();

    Optional<Reserva> getReservaById(Long id);

    //Metodo para crear una nueva reserva

    Reserva createReserva(Reserva reserva);

    //Metodo para actualizar una reserva existente

    Optional<Reserva> updateReserva(Long id, Reserva reserva);

    //Metodo para eliminar una reserva por su ID

    boolean deleteReserva(Long id);
    
}
