package cl.duoc.ofertaEv.dto;

import jakarta.validation.constraints.Positive;

public record CreateOfertaRequest(

    @Positive(message = "El ID de la subasta debe ser mayor a 0")
    int idSubasta,

    @Positive(message = "El ID del comprador debe ser mayor a 0")
    int idUsuarioComprador,

    @Positive(message = "El precio por kilo debe ser mayor a 0")
    int precioPorKg,

    @Positive(message = "La cantidad de kilos debe ser mayor a 0")
    int cantidadKg

) {
}