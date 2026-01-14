package com.ColombiaSolySelva.ColombiaSolYSelva.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class detallepedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_DetallePedido;

    @NotNull(message="Campo no puede estar vacío")
    private Integer id_Producto_DTPedido;

    @NotNull(message="Campo no puede estar vacío")
    private Integer id_pedido_DTPedido;

    @NotNull(message="Campo no puede estar vacío")
    private Integer cantidad_DTPedido;

    @NotNull(message="Campo no puede estar vacío")
    float subTotal_DTPedido;

    public detallepedido() {
    }

    public detallepedido(Long id_DetallePedido, Integer id_Producto_DTPedido, Integer id_pedido_DTPedido, Integer cantidad_DTPedido, float subTotal_DTPedido) {
        this.id_DetallePedido = id_DetallePedido;
        this.id_Producto_DTPedido = id_Producto_DTPedido;
        this.id_pedido_DTPedido = id_pedido_DTPedido;
        this.cantidad_DTPedido = cantidad_DTPedido;
        this.subTotal_DTPedido = subTotal_DTPedido;
    }

    public Long getId_DetallePedido() {
        return id_DetallePedido;
    }

    public void setId_DetallePedido(Long id_DetallePedido) {
        this.id_DetallePedido = id_DetallePedido;
    }

    public Integer getId_Producto_DTPedido() {
        return id_Producto_DTPedido;
    }

    public void setId_Producto_DTPedido(Integer id_Producto_DTPedido) {
        this.id_Producto_DTPedido = id_Producto_DTPedido;
    }

    public Integer getId_pedido_DTPedido() {
        return id_pedido_DTPedido;
    }

    public void setId_pedido_DTPedido(Integer id_pedido_DTPedido) {
        this.id_pedido_DTPedido = id_pedido_DTPedido;
    }

    public Integer getCantidad_DTPedido() {
        return cantidad_DTPedido;
    }

    public void setCantidad_DTPedido(Integer cantidad_DTPedido) {
        this.cantidad_DTPedido = cantidad_DTPedido;
    }

    public float getSubTotal_DTPedido() {
        return subTotal_DTPedido;
    }

    public void setSubTotal_DTPedido(float subTotal_DTPedido) {
        this.subTotal_DTPedido = subTotal_DTPedido;
    }

}
