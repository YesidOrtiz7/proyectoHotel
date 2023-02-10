package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.adaptador.out.db.RateRepository;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.TarifasPortOut;
import com.hotel.serviciosHotel.dominio.entidades.RateType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TarifasServiceTest {
    private TarifasPortOut portOutMock;
    private RateType rateTypeMock1=new RateType(1,"normal");
    private RateType rateTypeMock2=new RateType(2,"transportador");
    private List<RateType> listRateTipe=new ArrayList<>();

    private TarifasService service;

    @BeforeEach
    void setUp() {
        portOutMock= Mockito.mock(RateRepository.class);

        listRateTipe.add(rateTypeMock1);
        listRateTipe.add(rateTypeMock2);

        service=new TarifasService();
        service.setPortOut(portOutMock);
    }

    @Test
    void registrarTarifa() {
        Mockito.when(portOutMock.saveRate(rateTypeMock1)).thenReturn(rateTypeMock1);

        Assertions.assertEquals(rateTypeMock1,service.registrarTarifa(rateTypeMock1));
    }

    @Test
    void obtenerTarifas() {
        Mockito.when(portOutMock.getRates()).thenReturn(listRateTipe);

        Assertions.assertEquals(listRateTipe, service.obtenerTarifas());
    }

    @Test
    void obtenerTarifaPorId() {
        Mockito.when(portOutMock.getRateById(1)).thenReturn(Optional.of(rateTypeMock1));

        Assertions.assertEquals(rateTypeMock1,service.obtenerTarifaPorId(1));
    }

    @Test
    void actualizarTarifa() {
        Mockito.when(portOutMock.updateRate(rateTypeMock2)).thenReturn(rateTypeMock2);

        Assertions.assertEquals(rateTypeMock2,service.actualizarTarifa(rateTypeMock2));
    }

    @Test
    void eliminarTarifa() {
        Mockito.when(portOutMock.deleteRate(rateTypeMock2)).thenReturn(true);

        Assertions.assertEquals(true, service.eliminarTarifa(rateTypeMock2));
    }
}