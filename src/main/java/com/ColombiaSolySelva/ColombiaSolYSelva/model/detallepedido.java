package com.ColombiaSolySelva.ColombiaSolYSelva.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class detallepedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetallePedido;

    @NotNull(message="Campo no puede estar vacío")
    private Integer cantidadDTPedido;

    @NotNull(message="Campo no puede estar vacío")
    private BigDecimal subTotalDTPedido;

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

    public detallepedido(Long idDetallePedido, Integer cantidadDTPedido, BigDecimal subTotalDTPedido, pedidos pedido, producto producto) {
        this.idDetallePedido = idDetallePedido;
        this.cantidadDTPedido = cantidadDTPedido;
        this.subTotalDTPedido = subTotalDTPedido;
        this.pedido = pedido;
        this.producto = producto;
    }

    public Long getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(Long idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Integer getCantidadDTPedido() {
        return cantidadDTPedido;
    }

    public void setCantidadDTPedido(Integer cantidadDTPedido) {
        this.cantidadDTPedido = cantidadDTPedido;
    }

    public BigDecimal getSubTotalDTPedido() {
        return subTotalDTPedido;
    }

    public void calcularSubTotal() {
        if (producto == null || producto.getPrecioProducto() == null || cantidadDTPedido == null) {
            this.subTotalDTPedido = BigDecimal.ZERO;
            return;
        }

        this.subTotalDTPedido = producto.getPrecioProducto()
                .multiply(BigDecimal.valueOf(cantidadDTPedido));
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
