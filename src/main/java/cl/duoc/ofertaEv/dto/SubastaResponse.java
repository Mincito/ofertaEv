package cl.duoc.ofertaEv.dto;

import java.time.LocalDateTime;

public record SubastaResponse(
    int id,
    int idLote,
    LocalDateTime fechaInicio,
    LocalDateTime fechaCierre,
    int precioInicial,
    String estado
) {
}