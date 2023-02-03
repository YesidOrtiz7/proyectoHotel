package com.hotel.serviciosHotel.dominio.entidades;


public class Client {
    private int idCliente;
    private String documentoCliente;
    private String primerNombreCliente;
    private String segundoNombreCliente;
    private String primerApellidoCliente;
    private String segundoApellidoCliente;
    private String celularCliente;

    public Client(int idCliente, String documentoCliente, String primerNombreCliente, String segundoNombreCliente, String primerApellidoCliente, String segundoApellidoCliente, String celularCliente) {
        this.idCliente = idCliente;
        this.documentoCliente = documentoCliente;
        this.primerNombreCliente = primerNombreCliente;
        this.segundoNombreCliente = segundoNombreCliente;
        this.primerApellidoCliente = primerApellidoCliente;
        this.segundoApellidoCliente = segundoApellidoCliente;
        this.celularCliente = celularCliente;
    }

    public Client() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDocumentoCliente() {
        return documentoCliente;
    }

    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    public String getPrimerNombreCliente() {
        return primerNombreCliente;
    }

    public void setPrimerNombreCliente(String primerNombreCliente) {
        this.primerNombreCliente = primerNombreCliente;
    }

    public String getSegundoNombreCliente() {
        return segundoNombreCliente;
    }

    public void setSegundoNombreCliente(String segundoNombreCliente) {
        this.segundoNombreCliente = segundoNombreCliente;
    }

    public String getPrimerApellidoCliente() {
        return primerApellidoCliente;
    }

    public void setPrimerApellidoCliente(String primerApellidoCliente) {
        this.primerApellidoCliente = primerApellidoCliente;
    }

    public String getSegundoApellidoCliente() {
        return segundoApellidoCliente;
    }

    public void setSegundoApellidoCliente(String segundoApellidoCliente) {
        this.segundoApellidoCliente = segundoApellidoCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }
}
