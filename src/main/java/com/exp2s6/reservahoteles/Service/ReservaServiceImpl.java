package com.exp2s6.reservahoteles.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exp2s6.reservahoteles.Model.Reserva;
import com.exp2s6.reservahoteles.Repository.ReservaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Optional<Reserva> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    //Logica para crear una nueva reserva
    
    public Reserva createReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    //Logica para actualizar una reserva existente
    @Override
    public Optional<Reserva> updateReserva(Long id, Reserva reserva) {
        if(reservaRepository.existsById(id)){
            reserva.setId(id);
            return Optional.of(reservaRepository.save(reserva));
        } else {
            return Optional.empty();
        }
    }

    //Logica para eliminar una reserva por su ID
    @Override
    public boolean deleteReserva(Long id) {
        if(reservaRepository.existsById(id)){
            reservaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
