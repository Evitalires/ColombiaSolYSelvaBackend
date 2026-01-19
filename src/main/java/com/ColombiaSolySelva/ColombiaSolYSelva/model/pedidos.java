package com.ColombiaSolySelva.ColombiaSolYSelva.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
public class pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @NotNull(message="Campo no puede estar vacío")
    private Float valorPedido;

    @NotNull(message="Campo no puede estar vacío")
    private Date fechaPedido;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String transportadoraPedido;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=20)
    @JsonProperty("no_Guia_Pedido")
    private String NoGuiaPedido;

    //MANY TO ONE: Muchos pedidos pueden ser hechos por un cliente
    @ManyToOne
    @JoinColumn(name="id_FK_Cliente_Pedido")
    @JsonBackReference ("cliente-pedido")
    private cliente cliente;

    //ONE TO MANY: Un pedido tiene muchos detalles de pedido
    @OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference ("pedido-detalle")
    private List<detallepedido> detallepedido;

    public pedidos() {
    }

    public pedidos(Long idPedido, Float valorPedido, Date fechaPedido, String transportadoraPedido, String noGuiaPedido, cliente cliente, List<detallepedido> detallepedido) {
        this.idPedido = idPedido;
        this.valorPedido = valorPedido;
        this.fechaPedido = fechaPedido;
        this.transportadoraPedido = transportadoraPedido;
        NoGuiaPedido = noGuiaPedido;
        this.cliente = cliente;
        this.detallepedido = detallepedido;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Float getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(Float valorPedido) {
        this.valorPedido = valorPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getTransportadoraPedido() {
        return transportadoraPedido;
    }

    public void setTransportadoraPedido(String transportadoraPedido) {
        this.transportadoraPedido = transportadoraPedido;
    }

    public String getNoGuiaPedido() {
        return NoGuiaPedido;
    }

    public void setNoGuiaPedido(String noGuiaPedido) {
        NoGuiaPedido = noGuiaPedido;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }

    public List<detallepedido> getDetallepedido() {
        return detallepedido;
    }

    public void setDetallepedido(List<detallepedido> detallepedido) {
        this.detallepedido = detallepedido;
    }
}
