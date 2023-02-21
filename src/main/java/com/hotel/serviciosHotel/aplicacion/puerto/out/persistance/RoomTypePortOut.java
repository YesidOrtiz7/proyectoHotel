package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;


import com.hotel.serviciosHotel.dominio.entidades.RoomType;

import java.util.List;
import java.util.Optional;

public interface RoomTypePortOut {
    RoomType registrarTipo(RoomType type);
    List<RoomType> obtenerTipos();
    Optional<RoomType> obtenerTipoPorId(int id);
    RoomType actualizarTipo(RoomType type);
    boolean eliminarTipo(RoomType type);
    boolean eliminarTipoPorId(int idType);
    boolean tipoExistePorId(int idType);
}
