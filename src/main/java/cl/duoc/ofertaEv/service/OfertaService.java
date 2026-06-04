package cl.duoc.ofertaEv.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.ofertaEv.model.Oferta;
import cl.duoc.ofertaEv.repository.OfertaRepository;

@Service
public class OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    public List<Oferta> getAllOfertas() {
        return ofertaRepository.findAll();
    }

    public Oferta getById(Integer id) {
        return ofertaRepository.findById(id).orElse(null);
    }

    public Oferta guardarOferta(Oferta oferta) {
        oferta.setEstado("PENDIENTE");

        if (oferta.getFechaOferta() == null) {
            oferta.setFechaOferta(LocalDateTime.now());
        }

        return ofertaRepository.save(oferta);
    }

    public Oferta updateOferta(Oferta oferta) {
        return ofertaRepository.save(oferta);
    }

    public String deleteOferta(int id) {
        ofertaRepository.deleteById(id);
        return "Oferta eliminada del sistema!";
    }

    public int totalOfertas() {
        return ofertaRepository.totalOfertas();
    }

    public List<Oferta> obtenerPorSubasta(int idSubasta) {
        return ofertaRepository.selectPorSubasta(idSubasta);
    }

    public List<Oferta> obtenerPorComprador(int idUsuarioComprador) {
        return ofertaRepository.selectPorComprador(idUsuarioComprador);
    }

    public List<Oferta> obtenerPorEstado(String estado) {
        return ofertaRepository.selectPorEstado(estado);
    }

    public Oferta cancelarOferta(int id) {
        Oferta oferta = ofertaRepository.findById(id).orElse(null);

        if (oferta == null) {
            return null;
        }

        oferta.setEstado("CANCELADA");
        return ofertaRepository.save(oferta);
    }

    public Oferta marcarComoGanadora(int id) {
        Oferta oferta = ofertaRepository.findById(id).orElse(null);

        if (oferta == null) {
            return null;
        }

        oferta.setEstado("GANADORA");
        return ofertaRepository.save(oferta);
    }

    public Oferta marcarComoSuperada(int id) {
        Oferta oferta = ofertaRepository.findById(id).orElse(null);

        if (oferta == null) {
            return null;
        }

        oferta.setEstado("SUPERADA");
        return ofertaRepository.save(oferta);
    }
}