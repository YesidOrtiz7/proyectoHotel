package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

@Entity
@Table(name = "tblrecepcionista")
public class Recepcionista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecep;
    private String docRecep;
    private String nombresRecep;
    private String apellidosRecep;
    private String nombreUsuario;
    private String contrasena;
    private int recepEstado;

    public Recepcionista(String docRecep, String nombresRecep, String apellidosRecep, String nombreUsuario, String contrasena, int recepEstado) {
        this.docRecep = docRecep;
        this.nombresRecep = nombresRecep;
        this.apellidosRecep = apellidosRecep;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.recepEstado = recepEstado;
    }

    public int getIdRecep() {
        return idRecep;
    }

    public String getDocRecep() {
        return docRecep;
    }

    public void setDocRecep(String docRecep) {
        this.docRecep = docRecep;
    }

    public String getNombresRecep() {
        return nombresRecep;
    }

    public void setNombresRecep(String nombresRecep) {
        this.nombresRecep = nombresRecep;
    }

    public String getApellidosRecep() {
        return apellidosRecep;
    }

    public void setApellidosRecep(String apellidosRecep) {
        this.apellidosRecep = apellidosRecep;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getRecepEstado() {
        return recepEstado;
    }

    public void setRecepEstado(int recepEstado) {
        this.recepEstado = recepEstado;
    }
}
