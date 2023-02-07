package com.hotel.serviciosHotel.dominio.entidades;

public class ReceptionistEntity {
    private int idRecep;
    private String docRecep;
    private String receptionistNames;
    private String receptionistLastNames;

    public ReceptionistEntity() {
    }

    public ReceptionistEntity(int idRecep, String docRecep, String receptionistNames, String receptionistLastNames) {
        this.idRecep = idRecep;
        this.docRecep = docRecep;
        this.receptionistNames = receptionistNames;
        this.receptionistLastNames = receptionistLastNames;
    }

    public int getIdRecep() {
        return idRecep;
    }

    public void setIdRecep(int idRecep) {
        this.idRecep = idRecep;
    }

    public String getDocRecep() {
        return docRecep;
    }

    public void setDocRecep(String docRecep) {
        this.docRecep = docRecep;
    }

    public String getReceptionistNames() {
        return receptionistNames;
    }

    public void setReceptionistNames(String receptionistNames) {
        this.receptionistNames = receptionistNames;
    }

    public String getReceptionistLastNames() {
        return receptionistLastNames;
    }

    public void setReceptionistLastNames(String receptionistLastNames) {
        this.receptionistLastNames = receptionistLastNames;
    }
}
