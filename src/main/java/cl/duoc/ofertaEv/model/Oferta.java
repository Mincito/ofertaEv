package cl.duoc.ofertaEv.model;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tabla_ofertas")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_subasta", nullable = false)
    private int idSubasta;

    @Column(name = "id_usuario_comprador", nullable = false)
    private int idUsuarioComprador;

    @Column(name = "precio_por_kg", nullable = false)
    private int precioPorKg;

    @Column(name = "cantidad_kg", nullable = false)
    private int cantidadKg;

    @Column(name = "fecha_oferta", nullable = false)
    private LocalDateTime fechaOferta;

    @Column(name = "estado", nullable = false, length = 30)
    private String estado;

    public Oferta() {
    }

    public Oferta(int id, int idSubasta, int idUsuarioComprador, int precioPorKg, int cantidadKg,
            LocalDateTime fechaOferta, String estado) {
        this.id = id;
        this.idSubasta = idSubasta;
        this.idUsuarioComprador = idUsuarioComprador;
        this.precioPorKg = precioPorKg;
        this.cantidadKg = cantidadKg;
        this.fechaOferta = fechaOferta;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIdSubasta() {
        return idSubasta;
    }

    public void setIdSubasta(int idSubasta) {
        this.idSubasta = idSubasta;
    }

    public int getIdUsuarioComprador() {
        return idUsuarioComprador;
    }

    public void setIdUsuarioComprador(int idUsuarioComprador) {
        this.idUsuarioComprador = idUsuarioComprador;
    }

    public int getPrecioPorKg() {
        return precioPorKg;
    }

    public void setPrecioPorKg(int precioPorKg) {
        this.precioPorKg = precioPorKg;
    }

    public int getCantidadKg() {
        return cantidadKg;
    }

    public void setCantidadKg(int cantidadKg) {
        this.cantidadKg = cantidadKg;
    }

    public LocalDateTime getFechaOferta() {
        return fechaOferta;
    }

    public void setFechaOferta(LocalDateTime fechaOferta) {
        this.fechaOferta = fechaOferta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}