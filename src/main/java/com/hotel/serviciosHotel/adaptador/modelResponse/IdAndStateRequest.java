package com.hotel.serviciosHotel.adaptador.modelResponse;

public class IdAndStateRequest {
    private int id;
    private boolean state;
    private int query;

    public IdAndStateRequest() {
    }

    public IdAndStateRequest(int id, boolean state, int query) {
        this.id = id;
        this.state = state;
        this.query = query;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getQuery() {
        return query;
    }

    public void setQuery(int query) {
        this.query = query;
    }
}
