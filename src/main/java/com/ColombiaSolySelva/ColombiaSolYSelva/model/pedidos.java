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
    private Long id_Pedido;

    @NotNull(message="Campo no puede estar vacío")
    private Float valor_Pedido;

    @NotNull(message="Campo no puede estar vacío")
    private Date fecha_Pedido;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String transportadora_Pedido;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=20)
    @JsonProperty("no_Guia_Pedido")
    private String No_Guia_Pedido;

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

    public pedidos(Long id_Pedido, Float valor_Pedido, Date fecha_Pedido, Integer id_FK_Cliente_Pedido, String transportadora_Pedido, String No_Guia_Pedido) {
        this.id_Pedido = id_Pedido;
        this.valor_Pedido = valor_Pedido;
        this.fecha_Pedido = fecha_Pedido;
        this.transportadora_Pedido = transportadora_Pedido;
        this.No_Guia_Pedido = No_Guia_Pedido;
    }

    public Long getId_Pedido() {
        return id_Pedido;
    }

    public void setId_Pedido(Long id_Pedido) {
        this.id_Pedido = id_Pedido;
    }

    public Float getValor_Pedido() {
        return valor_Pedido;
    }

    public void setValor_Pedido(Float valor_Pedido) {
        this.valor_Pedido = valor_Pedido;
    }

    public Date getFecha_Pedido() {
        return fecha_Pedido;
    }

    public void setFecha_Pedido(Date fecha_Pedido) {
        this.fecha_Pedido = fecha_Pedido;
    }

    public String getTransportadora_Pedido() {
        return transportadora_Pedido;
    }

    public void setTransportadora_Pedido(String transportadora_Pedido) {
        this.transportadora_Pedido = transportadora_Pedido;
    }

    public String getNo_Guia_Pedido() {
        return No_Guia_Pedido;
    }

    public void setNo_Guia_Pedido(String No_Guia_Pedido) {
        this.No_Guia_Pedido = No_Guia_Pedido;
    }

    public cliente getCliente() {return cliente;
    }

    public void setCliente(cliente cliente) {this.cliente = cliente;
    }
}
