package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.adaptador.out.db.MunicipiosRepository;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.MunicipioPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Municipios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MunicipioServiceTest {
    private MunicipioPortOut portOutMock;

    private Municipios municipioMock1=new Municipios(1,"municipio1");
    private Municipios municipioMock2=new Municipios(2,"municipio2");
    private List<Municipios> lisMunicipiosMock=new ArrayList<>();
    private MunicipioService service;
    @BeforeEach
    void before(){
        portOutMock= Mockito.mock(MunicipiosRepository.class);

        lisMunicipiosMock.add(municipioMock1);
        lisMunicipiosMock.add(municipioMock2);

        service=new MunicipioService();
        service.setPortOut(portOutMock);
    }

    @Test
    void registrarMunicipio() {
        Mockito.when(portOutMock.registrarMunicipio(municipioMock1)).thenReturn(municipioMock1);

        Assertions.assertEquals(municipioMock1,service.registrarMunicipio(municipioMock1));
    }

    @Test
    void obtenerMunicipios() {
        Mockito.when(portOutMock.obtenerMunicipios()).thenReturn(lisMunicipiosMock);

        Assertions.assertEquals(lisMunicipiosMock,service.obtenerMunicipios());
    }

    @Test
    void obtenerMunicipioPorId() {
        Mockito.when(portOutMock.obtenerMunicipioPorId(1)).thenReturn(Optional.of(municipioMock1));

        Assertions.assertEquals(municipioMock1,service.obtenerMunicipioPorId(1));
    }
    @Test
    void obtenerMunicipioPorIdNull() {
        Mockito.when(portOutMock.obtenerMunicipioPorId(1)).thenReturn(null);

        Assertions.assertEquals(null,service.obtenerMunicipioPorId(1));
    }
    @Test
    void obtenerMunicipioPorIdEmpty() {
        Mockito.when(portOutMock.obtenerMunicipioPorId(1)).thenReturn(Optional.empty());

        Assertions.assertEquals(null,service.obtenerMunicipioPorId(1));
    }

    @Test
    void actualizarMunicipio() {
        Mockito.when(portOutMock.actualizarMunicipios(municipioMock2)).thenReturn(municipioMock2);

        Assertions.assertEquals(municipioMock2,service.actualizarMunicipio(municipioMock2));
    }

    @Test
    void eliminarMunicipio() {
        Mockito.when(portOutMock.eliminarMunicipio(municipioMock2)).thenReturn(true);

        Assertions.assertEquals(true,service.eliminarMunicipio(municipioMock2));
    }
}