package com.ColombiaSolySelva.ColombiaSolYSelva.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class detallepedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_DetallePedido;

    @NotNull(message="Campo no puede estar vacío")
    private Integer cantidad_DTPedido;

    @NotNull(message="Campo no puede estar vacío")
    private Float subTotal_DTPedido;

    //MANY TO ONE: Muchos detalles de pedido pueden estar en un pedido
    @ManyToOne
    @JoinColumn(name="id_pedido_DTPedido")
    @JsonBackReference ("pedido-detalle")
    private pedidos pedido;

    //MANY TO ONE: Muchos detalles de pedido pueden contener un producto
    @ManyToOne
    @JoinColumn(name="id_Producto_DTPedido")
    @JsonBackReference
    private producto producto;

    public detallepedido() {
    }

    public detallepedido(Long id_DetallePedido, Integer id_Producto_DTPedido, Integer id_pedido_DTPedido, Integer cantidad_DTPedido, Float subTotal_DTPedido) {
        this.id_DetallePedido = id_DetallePedido;
        this.cantidad_DTPedido = cantidad_DTPedido;
        this.subTotal_DTPedido = subTotal_DTPedido;

    }

    public Long getId_DetallePedido() {
        return id_DetallePedido;
    }

    public void setId_DetallePedido(Long id_DetallePedido) {
        this.id_DetallePedido = id_DetallePedido;
    }

    public Integer getCantidad_DTPedido() {
        return cantidad_DTPedido;
    }

    public void setCantidad_DTPedido(Integer cantidad_DTPedido) {
        this.cantidad_DTPedido = cantidad_DTPedido;
    }

    public Float getSubTotal_DTPedido() {
        return subTotal_DTPedido;
    }

    public void setSubTotal_DTPedido(Float subTotal_DTPedido) {
        this.subTotal_DTPedido = subTotal_DTPedido;
    }

    public pedidos getPedido() {
        return pedido;
    }

    public void setPedido(pedidos pedido) {
        this.pedido = pedido;
    }

    public producto getProducto() {
        return producto;
    }

    public void setProducto(producto producto) {
        this.producto = producto;
    }
}
