package com.exp2s6.reservahoteles.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "fechaReserva", "numeroHabitacion", "nombreCliente", "fechaIngreso", "fechaSalida"})

@Entity
@Table(name = "reservas")   
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha_reserva")
    @NotNull(message = "La fecha de reserva es obligatoria")
    private String fechaReserva;

    @Column(name = "nombre_cliente")
    @NotNull(message = "El nombre del cliente es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre del cliente debe tener entre 2 y 100 caracteres")
    private String nombreCliente;

    @Column(name = "numero_habitacion")
    @NotNull(message = "El número de habitación es obligatorio")
    @Min(value = 100, message = "El número de habitación debe ser al menos 100")
    @Max(value = 999, message = "El número de habitación debe ser como máximo 999")
    private Integer numeroHabitacion;

    @Column(name = "fecha_ingreso")
    @NotNull(message = "La fecha de entrada es obligatoria")
    private String fechaIngreso;

    @Column(name = "fecha_salida")
    @NotNull(message = "La fecha de salida es obligatoria")
    private String fechaSalida;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(Integer numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

}
