package com.example.veterinariaIntegrador.model;

import com.example.veterinariaIntegrador.Enums.Estado;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {
    private int id_turno;
    private LocalDate fecha;
    private LocalTime hora ;
    private String motivo;
    private Estado estado;
    private int id_cliente;
    private int id_veterinario;
    private int id_mascota;

    public Turno(){}

    public Turno(LocalDate fecha, LocalTime hora, String motivo, Estado estado, int id_cliente, int id_veterinario, int id_mascota) {
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estado = estado;
        this.id_cliente = id_cliente;
        this.id_veterinario = id_veterinario;
        this.id_mascota = id_mascota;
    }

    public int getId_turno() {
        return id_turno;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_veterinario() {
        return id_veterinario;
    }

    public void setId_veterinario(int id_veterinario) {
        this.id_veterinario = id_veterinario;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id_turno=" + id_turno +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", motivo='" + motivo + '\'' +
                ", estado=" + estado +
                ", id_cliente=" + id_cliente +
                ", id_veterinario=" + id_veterinario +
                ", id_mascota=" + id_mascota +
                '}';
    }
}
