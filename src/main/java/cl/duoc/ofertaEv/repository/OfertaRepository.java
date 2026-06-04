package cl.duoc.ofertaEv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cl.duoc.ofertaEv.model.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {

    @Query(value = "SELECT * FROM tabla_ofertas WHERE id_subasta = :idSubasta", nativeQuery = true)
    List<Oferta> selectPorSubasta(@Param("idSubasta") int idSubasta);

    @Query(value = "SELECT * FROM tabla_ofertas WHERE id_usuario_comprador = :idUsuarioComprador", nativeQuery = true)
    List<Oferta> selectPorComprador(@Param("idUsuarioComprador") int idUsuarioComprador);

    @Query(value = "SELECT * FROM tabla_ofertas WHERE estado = :estado", nativeQuery = true)
    List<Oferta> selectPorEstado(@Param("estado") String estado);

    default int totalOfertas() {
        return (int) this.count();
    }
}