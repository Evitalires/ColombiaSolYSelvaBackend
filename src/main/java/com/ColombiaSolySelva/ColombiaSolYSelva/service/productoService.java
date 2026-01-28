package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.producto;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IproductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class productoService implements IproductoService {
    private final IproductoRepository productoRepository;
    private final String uploadDirectory = "src/main/resources/static/IMG/imgProductos/";

    @Autowired
    public productoService(IproductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public String guardarImagen(MultipartFile archivo) throws IOException {
        if (archivo == null || archivo.isEmpty()) {
            return "static/IMG/imgProductos/default.png";
        }

        Path directoryPath = Paths.get(uploadDirectory);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        String nombreOriginal = archivo.getOriginalFilename();
        String extension = "";
        if (nombreOriginal != null && nombreOriginal.contains(".")) {
            extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
        }
        String nuevoNombre = UUID.randomUUID().toString() + extension;
        Path rutaArchivo = directoryPath.resolve(nuevoNombre);

        Files.copy(archivo.getInputStream(), rutaArchivo);

        return "backend/src/main/resources/static/IMG/imgProductos/" + nuevoNombre;
    }

    @Override
    public List<producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public void guardarProducto(producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public void editarProducto(Long id, producto productoActualizado) {
        producto productoExistente = productoRepository.findById(id).orElse(null);

        if (productoExistente != null) {
            productoExistente.setNombreProducto(productoActualizado.getNombreProducto());
            productoExistente.setPrecioProducto(productoActualizado.getPrecioProducto());
            productoExistente.setDescripcionProducto(productoActualizado.getDescripcionProducto());
            productoExistente.setCategoriaProducto(productoActualizado.getCategoriaProducto());
            productoExistente.setStockProducto(productoActualizado.getStockProducto());
            productoExistente.setImagenProducto(productoActualizado.getImagenProducto());

            productoRepository.save(productoExistente);
        } else {
            throw new RuntimeException("Producto no encontrado con el id: " + id);
        }
    }
}
