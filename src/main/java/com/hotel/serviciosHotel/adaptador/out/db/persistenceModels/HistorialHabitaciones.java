package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name="tblhistorialhabitaciones")
public class HistorialHabitaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private int idHistorial;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ServicioId",nullable = false)
    private Servicio idServicio;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHabitacion",nullable = false)
    private Habitacion idHabitacion;
    @NotNull
    private LocalDateTime desde;
    @NotNull
    private LocalDateTime hasta;

    public HistorialHabitaciones() {
    }

    public HistorialHabitaciones(int idHistorial, Servicio idServicio, Habitacion idHabitacion, LocalDateTime desde, LocalDateTime hasta) {
        this.idHistorial = idHistorial;
        this.idServicio = idServicio;
        this.idHabitacion = idHabitacion;
        this.desde = desde;
        this.hasta = hasta;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Servicio getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Servicio idServicio) {
        this.idServicio = idServicio;
    }

    public Habitacion getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Habitacion idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public LocalDateTime getDesde() {
        return desde;
    }

    public void setDesde(LocalDateTime desde) {
        this.desde = desde;
    }

    public LocalDateTime getHasta() {
        return hasta;
    }

    public void setHasta(LocalDateTime hasta) {
        this.hasta = hasta;
    }
}
