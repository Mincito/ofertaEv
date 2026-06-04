package cl.duoc.ofertaEv.mapper;

import cl.duoc.ofertaEv.dto.CreateOfertaRequest;
import cl.duoc.ofertaEv.dto.UpdateOfertaRequest;
import cl.duoc.ofertaEv.model.Oferta;

public class OfertaMapper {

    public static Oferta toModel(CreateOfertaRequest request) {
        return new Oferta(
                0,
                request.idSubasta(),
                request.idUsuarioComprador(),
                request.precioPorKg(),
                request.cantidadKg(),
                null,
                null
        );
    }

    public static Oferta toModel(int id, UpdateOfertaRequest request) {
        return new Oferta(
                id,
                request.idSubasta(),
                request.idUsuarioComprador(),
                request.precioPorKg(),
                request.cantidadKg(),
                request.fechaOferta(),
                request.estado()
        );
    }
}