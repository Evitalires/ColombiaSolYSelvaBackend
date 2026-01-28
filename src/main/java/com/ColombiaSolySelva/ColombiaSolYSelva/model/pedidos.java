package com.ColombiaSolySelva.ColombiaSolYSelva.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @NotNull(message="Campo no puede estar vacío")
    private BigDecimal valorPedido;

    @NotNull(message="Campo no puede estar vacío")
    private Date fechaPedido;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String transportadoraPedido;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=20)
    @JsonProperty("no_Guia_Pedido")
    private String NoGuiaPedido;

    @Column(name = "direccion_pedido")
    private String direccionPedido;

    @Column(name = "ciudad_pedido")
    private String ciudadPedido;

    @Column(name = "metodo_pago")
    private String metodoPago;

    //MANY TO ONE: Muchos pedidos pueden ser hechos por un cliente
    @ManyToOne
    @JoinColumn(name="id_FK_Cliente_Pedido")
    @JsonBackReference ("cliente-pedido")
    private cliente cliente;

    //ONE TO MANY: Un pedido tiene muchos detalles de pedido
    @OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference ("pedido-detalle")
    private List<detallepedido> detallepedido;

    //MercadoPago
    private String mpPreferenceId;
    private String mpPaymentId;
    private String estadoPago;

    public pedidos() {
    }

    public pedidos(Long idPedido, BigDecimal valorPedido, Date fechaPedido, String transportadoraPedido, String noGuiaPedido, String direccionPedido, String ciudadPedido, String metodoPago, cliente cliente, List<detallepedido> detallepedido, String mpPreferenceId, String mpPaymentId, String estadoPago) {
        this.idPedido = idPedido;
        this.valorPedido = valorPedido;
        this.fechaPedido = fechaPedido;
        this.transportadoraPedido = transportadoraPedido;
        NoGuiaPedido = noGuiaPedido;
        this.direccionPedido = direccionPedido;
        this.ciudadPedido = ciudadPedido;
        this.metodoPago = metodoPago;
        this.cliente = cliente;
        this.detallepedido = detallepedido;
        this.mpPreferenceId = mpPreferenceId;
        this.mpPaymentId = mpPaymentId;
        this.estadoPago = estadoPago;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public BigDecimal getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(BigDecimal valorPedido) {
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

    public String getDireccionPedido() {
        return direccionPedido;
    }

    public void setDireccionPedido(String direccionPedido) {
        this.direccionPedido = direccionPedido;
    }

    public String getCiudadPedido() {
        return ciudadPedido;
    }

    public void setCiudadPedido(String ciudadPedido) {
        this.ciudadPedido = ciudadPedido;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
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

    public String getMpPreferenceId() {
        return mpPreferenceId;
    }

    public void setMpPreferenceId(String mpPreferenceId) {
        this.mpPreferenceId = mpPreferenceId;
    }

    public String getMpPaymentId() {
        return mpPaymentId;
    }

    public void setMpPaymentId(String mpPaymentId) {
        this.mpPaymentId = mpPaymentId;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
}