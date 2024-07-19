package com.hotel.serviciosHotel.dominio.entidades;

public class RoomStatus {
    private int idStatus;
    private String statusName;
    private boolean visibleOnSelection;
    private boolean defaultForServiceStart;
    private boolean defaultForServiceShutdown;

    public RoomStatus(int idStatus, String statusName, boolean visibleOnSelection, boolean defaultForServiceStart, boolean defaultForServiceShutdown) {
        this.idStatus = idStatus;
        this.statusName = statusName;
        this.visibleOnSelection = visibleOnSelection;
        this.defaultForServiceStart=defaultForServiceStart;
        this.defaultForServiceShutdown = defaultForServiceShutdown;
    }

    public RoomStatus() {
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public boolean isVisibleOnSelection() {
        return visibleOnSelection;
    }

    public void setVisibleOnSelection(boolean visibleOnSelection) {
        this.visibleOnSelection = visibleOnSelection;
    }

    public boolean isDefaultForServiceStart() {
        return defaultForServiceStart;
    }

    public void setDefaultForServiceStart(boolean defaultForServiceStart) {
        this.defaultForServiceStart = defaultForServiceStart;
    }

    public boolean isDefaultForServiceShutdown() {
        return defaultForServiceShutdown;
    }

    public void setDefaultForServiceShutdown(boolean defaultForServiceShutdown) {
        this.defaultForServiceShutdown = defaultForServiceShutdown;
    }
}
