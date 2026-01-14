package com.ColombiaSolySelva.ColombiaSolYSelva.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
public class pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Pedido;

    @NotNull(message="Campo no puede estar vacío")
    private float valor_Pedido;

    @NotNull(message="Campo no puede estar vacío")
    private Date fecha_Pedido;

    @NotNull(message="Campo no puede estar vacío")
    private Integer id_FK_Cliente_Pedido;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String transportadora_Pedido;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=20)
    private String No_Guia_Pedido;

    public pedidos() {
    }

    public pedidos(Long id_Pedido, float valor_Pedido, Date fecha_Pedido, Integer id_FK_Cliente_Pedido, String transportadora_Pedido, String no_Guia_Pedido) {
        this.id_Pedido = id_Pedido;
        this.valor_Pedido = valor_Pedido;
        this.fecha_Pedido = fecha_Pedido;
        this.id_FK_Cliente_Pedido = id_FK_Cliente_Pedido;
        this.transportadora_Pedido = transportadora_Pedido;
        No_Guia_Pedido = no_Guia_Pedido;
    }

    public Long getId_Pedido() {
        return id_Pedido;
    }

    public void setId_Pedido(Long id_Pedido) {
        this.id_Pedido = id_Pedido;
    }

    public float getValor_Pedido() {
        return valor_Pedido;
    }

    public void setValor_Pedido(float valor_Pedido) {
        this.valor_Pedido = valor_Pedido;
    }

    public Date getFecha_Pedido() {
        return fecha_Pedido;
    }

    public void setFecha_Pedido(Date fecha_Pedido) {
        this.fecha_Pedido = fecha_Pedido;
    }

    public Integer getId_FK_Cliente_Pedido() {
        return id_FK_Cliente_Pedido;
    }

    public void setId_FK_Cliente_Pedido(Integer id_FK_Cliente_Pedido) {
        this.id_FK_Cliente_Pedido = id_FK_Cliente_Pedido;
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

    public void setNo_Guia_Pedido(String no_Guia_Pedido) {
        No_Guia_Pedido = no_Guia_Pedido;
    }
}
