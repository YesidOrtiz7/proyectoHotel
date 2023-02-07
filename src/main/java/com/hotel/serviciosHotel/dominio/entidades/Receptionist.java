package com.hotel.serviciosHotel.dominio.entidades;


public class Receptionist extends ReceptionistEntity{

    private String userName;
    private String password;
    private int receptionistState;

    public Receptionist() {
    }

    public Receptionist(int idRecep, String docRecep, String receptionistNames, String receptionistLastNames, String userName, String password, int receptionistState) {
        super(idRecep,docRecep,receptionistNames,receptionistLastNames);
        this.userName = userName;
        this.password = password;
        this.receptionistState = receptionistState;
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
