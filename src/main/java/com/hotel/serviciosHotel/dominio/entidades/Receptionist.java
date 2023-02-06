package com.hotel.serviciosHotel.dominio.entidades;

public class Receptionist {
    private int idRecep;
    private String docRecep;
    private String receptionistNames;
    private String receptionistLastNames;
    private String userName;
    private String password;
    private int receptionistState;

    public Receptionist() {
    }

    public Receptionist(int idRecep, String docRecep, String receptionistNames, String receptionistLastNames, String userName, String password, int receptionistState) {
        this.idRecep = idRecep;
        this.docRecep = docRecep;
        this.receptionistNames = receptionistNames;
        this.receptionistLastNames = receptionistLastNames;
        this.userName = userName;
        this.password = password;
        this.receptionistState = receptionistState;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getReceptionistState() {
        return receptionistState;
    }

    public void setReceptionistState(int receptionistState) {
        this.receptionistState = receptionistState;
    }
}
