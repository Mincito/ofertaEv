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

@RestController
@RequestMapping("/api/v1/ofertas")
public class OfertaController {

    private final OfertaService ofertaService;

    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    @GetMapping
    public ResponseEntity<List<Oferta>> getAllOfertas() {
        List<Oferta> ofertas = ofertaService.getAllOfertas();
        return ResponseEntity.ok(ofertas);
    }

    @PostMapping
    public ResponseEntity<Oferta> guardarOferta(@Valid @RequestBody CreateOfertaRequest request) {
        Oferta oferta = OfertaMapper.toModel(request);
        Oferta ofertaGuardada = ofertaService.guardarOferta(oferta);

        return ResponseEntity.status(HttpStatus.CREATED).body(ofertaGuardada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oferta> getById(@PathVariable Integer id) {
        Oferta oferta = ofertaService.getById(id);

        if (oferta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oferta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Oferta> updateOferta(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateOfertaRequest request) {

        Oferta ofertaExistente = ofertaService.getById(id);

        if (ofertaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        Oferta oferta = OfertaMapper.toModel(id, request);
        Oferta ofertaActualizada = ofertaService.updateOferta(oferta);

        return ResponseEntity.ok(ofertaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOferta(@PathVariable int id) {
        Oferta oferta = ofertaService.getById(id);

        if (oferta == null) {
            return ResponseEntity.notFound().build();
        }

        ofertaService.deleteOferta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> totalOfertas() {
        int total = ofertaService.totalOfertas();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/subasta/{idSubasta}")
    public ResponseEntity<List<Oferta>> obtenerPorSubasta(@PathVariable int idSubasta) {
        List<Oferta> ofertas = ofertaService.obtenerPorSubasta(idSubasta);
        return ResponseEntity.ok(ofertas);
    }

    @GetMapping("/comprador/{idUsuarioComprador}")
    public ResponseEntity<List<Oferta>> obtenerPorComprador(@PathVariable int idUsuarioComprador) {
        List<Oferta> ofertas = ofertaService.obtenerPorComprador(idUsuarioComprador);
        return ResponseEntity.ok(ofertas);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Oferta>> obtenerPorEstado(@PathVariable String estado) {
        List<Oferta> ofertas = ofertaService.obtenerPorEstado(estado);
        return ResponseEntity.ok(ofertas);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Oferta> cancelarOferta(@PathVariable int id) {
        Oferta oferta = ofertaService.cancelarOferta(id);

        if (oferta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oferta);
    }

    @PutMapping("/{id}/ganadora")
    public ResponseEntity<Oferta> marcarComoGanadora(@PathVariable int id) {
        Oferta oferta = ofertaService.marcarComoGanadora(id);

        if (oferta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oferta);
    }

    @PutMapping("/{id}/superada")
    public ResponseEntity<Oferta> marcarComoSuperada(@PathVariable int id) {
        Oferta oferta = ofertaService.marcarComoSuperada(id);

        if (oferta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oferta);
    }
}