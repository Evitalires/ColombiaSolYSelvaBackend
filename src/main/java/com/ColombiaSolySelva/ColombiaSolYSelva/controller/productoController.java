package com.ColombiaSolySelva.ColombiaSolYSelva.controller;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.producto;
import com.ColombiaSolySelva.ColombiaSolYSelva.service.productoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.io.IOException;

@RestController
@RequestMapping("/producto")
public class productoController {
    private final productoService productoService;

    @Autowired
    public productoController(productoService productosService) {
        this.productoService = productosService;
    }

    @GetMapping
    public List<producto> listaproductos() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/id/{id}")
    public Optional<producto> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping(value = "/crear-producto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<producto> crearProducto(
            @RequestParam("nombre") String nombre,
            @RequestParam("precio") BigDecimal precio,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("categoria") String categoria,
            @RequestParam("stock") Integer stock,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {

        // 1. Guardar la imagen en el sistema de archivos
        String rutaImagen = productoService.guardarImagen(imagen);

        // 2. Crear objeto producto y guardar en BD
        producto nuevoProducto = new producto();
        nuevoProducto.setNombreProducto(nombre);
        nuevoProducto.setPrecioProducto(precio);
        nuevoProducto.setDescripcionProducto(descripcion);
        nuevoProducto.setCategoriaProducto(categoria);
        nuevoProducto.setStockProducto(stock);
        nuevoProducto.setImagenProducto(rutaImagen);

        productoService.guardarProducto(nuevoProducto);

        return ResponseEntity.ok(nuevoProducto);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> pedidosProductos(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.ok("Producto eliminado con éxito");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarProductos(@PathVariable Long id, @RequestBody producto productoActualizado) {
        productoService.editarProducto(id, productoActualizado);
        return ResponseEntity.ok("Producto editado exitosamente");
    }
}
