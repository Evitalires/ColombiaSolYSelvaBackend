package com.ColombiaSolySelva.ColombiaSolYSelva.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String nombreCliente;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String apellidoCliente;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String contrasenaCliente;

    @NotNull(message="Campo no puede estar vacío")
    private Long telCliente;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String correoCliente;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String direccionCliente;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String ciudadCliente;

    // ONE TO MANY: Un cliente puede tener muchos pedidos
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference ("cliente-pedido")
    private List<pedidos> pedidos;

    public cliente() {
    }

    public cliente(Long idCliente, String nombreCliente, String apellidoCliente, String contrasenaCliente, Long telCliente, String correoCliente, String direccionCliente, String ciudadCliente, List<pedidos> pedidos) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.contrasenaCliente = contrasenaCliente;
        this.telCliente = telCliente;
        this.correoCliente = correoCliente;
        this.direccionCliente = direccionCliente;
        this.ciudadCliente = ciudadCliente;
        this.pedidos = pedidos;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getContrasenaCliente() {
        return contrasenaCliente;
    }

    public void setContrasenaCliente(String contrasenaCliente) {
        this.contrasenaCliente = contrasenaCliente;
    }

    public Long getTelCliente() {
        return telCliente;
    }

    public void setTelCliente(Long telCliente) {
        this.telCliente = telCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getCiudadCliente() {
        return ciudadCliente;
    }

    public void setCiudadCliente(String ciudadCliente) {
        this.ciudadCliente = ciudadCliente;
    }

    public List<pedidos> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<pedidos> pedidos) {
        this.pedidos = pedidos;
    }
}

