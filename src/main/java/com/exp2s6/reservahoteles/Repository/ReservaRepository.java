package com.exp2s6.reservahoteles.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exp2s6.reservahoteles.Model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
}
