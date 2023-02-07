package com.hotel.serviciosHotel.adaptador.out.db.mappers;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.TipoPago;
import com.hotel.serviciosHotel.dominio.entidades.PaymentType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MapperTipoPago {
    @Mappings({
            @Mapping(source = "idPago",target = "idPago"),
            @Mapping(source = "descripcionPago",target = "descripcionPago"),
    })
    TipoPago toTipoPago(PaymentType paymentType);

    @InheritInverseConfiguration
    PaymentType toPaymentType(TipoPago tipoPago);
}
