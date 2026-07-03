package cl.duoc.ofertaEv.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;

import cl.duoc.ofertaEv.dto.CreateOfertaRequest;
import cl.duoc.ofertaEv.dto.UpdateOfertaRequest;
import cl.duoc.ofertaEv.mapper.OfertaMapper;
import cl.duoc.ofertaEv.model.Oferta;
import cl.duoc.ofertaEv.service.OfertaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "Ofertas",
    description = "Operaciones relacionadas con la gestión de ofertas"
)
@RestController
@RequestMapping("/api/v1/ofertas")
public class OfertaController {

    private final OfertaService ofertaService;

    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    @Operation(
        summary = "Listar ofertas",
        description = "Obtiene todas las ofertas registradas en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de ofertas obtenida correctamente"
    )
    @GetMapping
    public ResponseEntity<List<Oferta>> getAllOfertas() {
        List<Oferta> ofertas = ofertaService.getAllOfertas();
        return ResponseEntity.ok(ofertas);
    }

    @Operation(
        summary = "Crear oferta",
        description = "Registra una oferta asociada a una subasta abierta"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Oferta creada correctamente"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos o subasta no disponible"
        )
    })
    @PostMapping
    public ResponseEntity<Oferta> guardarOferta(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos necesarios para registrar una oferta",
                required = true,
                content = @Content(
                    schema = @Schema(implementation = CreateOfertaRequest.class),
                    examples = @ExampleObject(
                        name = "Ejemplo de oferta",
                        value = """
                        {
                          "idSubasta": 1,
                          "idUsuarioComprador": 2,
                          "precioPorKg": 3500,
                          "cantidadKg": 100
                        }
                        """
                    )
                )
            )
            @Valid @RequestBody CreateOfertaRequest request) {

        Oferta oferta = OfertaMapper.toModel(request);
        Oferta ofertaGuardada = ofertaService.guardarOferta(oferta);

        return ResponseEntity.status(HttpStatus.CREATED).body(ofertaGuardada);
    }

    @Operation(
        summary = "Buscar oferta por ID",
        description = "Obtiene una oferta según su identificador"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Oferta encontrada"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Oferta no encontrada"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Oferta> getById(@PathVariable Integer id) {
        Oferta oferta = ofertaService.getById(id);

        if (oferta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oferta);
    }

    @Operation(
        summary = "Actualizar oferta",
        description = "Modifica los datos de una oferta existente"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Oferta actualizada correctamente"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Los datos enviados no son válidos"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Oferta no encontrada"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Oferta> updateOferta(
            @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos actualizados de la oferta",
                required = true,
                content = @Content(
                    schema = @Schema(implementation = UpdateOfertaRequest.class),
                    examples = @ExampleObject(
                        name = "Ejemplo de actualización",
                        value = """
                        {
                          "idSubasta": 1,
                          "idUsuarioComprador": 2,
                          "precioPorKg": 3800,
                          "cantidadKg": 100,
                          "fechaOferta": "2026-07-03T12:30:00",
                          "estado": "PENDIENTE"
                        }
                        """
                    )
                )
            )
            @Valid @RequestBody UpdateOfertaRequest request) {

        Oferta ofertaExistente = ofertaService.getById(id);

        if (ofertaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        Oferta oferta = OfertaMapper.toModel(id, request);
        Oferta ofertaActualizada = ofertaService.updateOferta(oferta);

        return ResponseEntity.ok(ofertaActualizada);
    }

    @Operation(
        summary = "Eliminar oferta",
        description = "Elimina una oferta según su identificador"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Oferta eliminada correctamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Oferta no encontrada"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOferta(@PathVariable int id) {
        Oferta oferta = ofertaService.getById(id);

        if (oferta == null) {
            return ResponseEntity.notFound().build();
        }

        ofertaService.deleteOferta(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Obtener total de ofertas",
        description = "Devuelve la cantidad total de ofertas registradas"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Total de ofertas obtenido correctamente"
    )
    @GetMapping("/total")
    public ResponseEntity<Integer> totalOfertas() {
        int total = ofertaService.totalOfertas();
        return ResponseEntity.ok(total);
    }

    @Operation(
        summary = "Buscar ofertas por subasta",
        description = "Obtiene todas las ofertas asociadas a una subasta"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Ofertas obtenidas correctamente"
    )
    @GetMapping("/subasta/{idSubasta}")
    public ResponseEntity<List<Oferta>> obtenerPorSubasta(@PathVariable int idSubasta) {
        List<Oferta> ofertas = ofertaService.obtenerPorSubasta(idSubasta);
        return ResponseEntity.ok(ofertas);
    }

    @Operation(
        summary = "Buscar ofertas por comprador",
        description = "Obtiene todas las ofertas realizadas por un comprador"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Ofertas obtenidas correctamente"
    )
    @GetMapping("/comprador/{idUsuarioComprador}")
    public ResponseEntity<List<Oferta>> obtenerPorComprador(@PathVariable int idUsuarioComprador) {
        List<Oferta> ofertas = ofertaService.obtenerPorComprador(idUsuarioComprador);
        return ResponseEntity.ok(ofertas);
    }

    @Operation(
        summary = "Buscar ofertas por estado",
        description = "Obtiene las ofertas que tengan el estado indicado"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Ofertas obtenidas correctamente"
    )
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Oferta>> obtenerPorEstado(@PathVariable String estado) {
        List<Oferta> ofertas = ofertaService.obtenerPorEstado(estado);
        return ResponseEntity.ok(ofertas);
    }

    @Operation(
        summary = "Cancelar oferta",
        description = "Cambia el estado de una oferta a CANCELADA"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Oferta cancelada correctamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Oferta no encontrada"
        )
    })
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Oferta> cancelarOferta(@PathVariable int id) {
        Oferta oferta = ofertaService.cancelarOferta(id);

        if (oferta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oferta);
    }

    @Operation(
        summary = "Marcar oferta como ganadora",
        description = "Cambia el estado de una oferta a GANADORA"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Oferta marcada como ganadora"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Oferta no encontrada"
        )
    })
    @PutMapping("/{id}/ganadora")
    public ResponseEntity<Oferta> marcarComoGanadora(@PathVariable int id) {
        Oferta oferta = ofertaService.marcarComoGanadora(id);

        if (oferta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oferta);
    }

    @Operation(
        summary = "Marcar oferta como superada",
        description = "Cambia el estado de una oferta a SUPERADA"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Oferta marcada como superada"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Oferta no encontrada"
        )
    })
    @PutMapping("/{id}/superada")
    public ResponseEntity<Oferta> marcarComoSuperada(@PathVariable int id) {
        Oferta oferta = ofertaService.marcarComoSuperada(id);

        if (oferta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oferta);
    }
}    