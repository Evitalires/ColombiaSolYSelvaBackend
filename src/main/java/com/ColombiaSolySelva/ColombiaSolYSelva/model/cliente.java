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
    private Long id_Cliente;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String nombre_cliente;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String apellido_Cliente;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String contrasena_Cliente;

    @NotNull(message="Campo no puede estar vacío")
    private Long tel_Cliente;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    @Column(name="correo_Cliente")
    private String correoCliente;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String direccion_Cliente;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String ciudad_Cliente;

    // ONE TO MANY: Un cliente puede tener muchos pedidos
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference ("cliente-pedido")
    private List<pedidos> pedidos;

    public cliente() {
    }

    public cliente(Long id_Cliente, String nombre_cliente, String apellido_Cliente, String contrasena_Cliente, Long tel_Cliente, String correoCliente, String direccion_Cliente, String ciudad_Cliente) {
        this.id_Cliente = id_Cliente;
        this.nombre_cliente = nombre_cliente;
        this.apellido_Cliente = apellido_Cliente;
        this.contrasena_Cliente = contrasena_Cliente;
        this.tel_Cliente = tel_Cliente;
        this.correoCliente = correoCliente;
        this.direccion_Cliente = direccion_Cliente;
        this.ciudad_Cliente = ciudad_Cliente;
    }

    public Long getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(Long id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_Cliente() {
        return apellido_Cliente;
    }

    public void setApellido_Cliente(String apellido_Cliente) {
        this.apellido_Cliente = apellido_Cliente;
    }

    public String getContrasena_Cliente() {
        return contrasena_Cliente;
    }

    public void setContrasena_Cliente(String contrasena_Cliente) {
        this.contrasena_Cliente = contrasena_Cliente;
    }

    public Long getTel_Cliente() {
        return tel_Cliente;
    }

    public void setTel_Cliente(Long tel_Cliente) {
        this.tel_Cliente = tel_Cliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getDireccion_Cliente() {
        return direccion_Cliente;
    }

    public void setDireccion_Cliente(String direccion_Cliente) {
        this.direccion_Cliente = direccion_Cliente;
    }

    public String getCiudad_Cliente() {
        return ciudad_Cliente;
    }

    public void setCiudad_Cliente(String ciudad_Cliente) {
        this.ciudad_Cliente = ciudad_Cliente;
    }


}

