package com.ColombiaSolySelva.ColombiaSolYSelva.config;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.producto;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IproductoRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DataLoader implements CommandLineRunner {

  private final IproductoRepository productoRepository;

  private final ObjectMapper objectMapper = new ObjectMapper();

  public DataLoader(IproductoRepository productoRepository) {
    this.productoRepository = productoRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    if (productoRepository.count() == 0) {
      loadProductos();
    }
  }

  private void loadProductos() {
    String json = """
        [
          {
            "id": 1,
            "nombre": "Mochila Wayuu",
            "precio": 120000,
            "descripcion": "Mochila tejida a mano por artesanas de La Guajira.",
            "categoria": "Moda y Accesorios",
            "stock": 10,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/mochila.jpg"
          },
          {
            "id": 2,
            "nombre": "Pulsera Wayuu Tejida",
            "precio": 35000,
            "descripcion": "Pulsera hecha a mano con diseños tradicionales Wayuu.",
            "categoria": "Moda y Accesorios",
            "stock": 15,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/pulceraWayu.jpg"
          },
          {
            "id": 3,
            "nombre": "Aretes Sombrero Vueltiao",
            "precio": 95000,
            "descripcion": "Aretes elaborados con técnica ancestral de filigrana en Mompox.",
            "categoria": "Moda y Accesorios",
            "stock": 7,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/aretes.jpg"
          },
          {
            "id": 4,
            "nombre": "Bolso de Fique Tejido",
            "precio": 78000,
            "descripcion": "Bolso artesanal hecho en fique por artesanos de Santander.",
            "categoria": "Moda y Accesorios",
            "stock": 12,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/bolso.jpg"
          },
          {
            "id": 5,
            "nombre": "Figura de Madera Tallada",
            "precio": 85000,
            "descripcion": "Escultura artesanal elaborada en madera de guayacán.",
            "categoria": "Decoración para el Hogar",
            "stock": 5,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/figuraMadera.jpg"
          },
          {
            "id": 6,
            "nombre": "Tapiz Tejido",
            "precio": 98000,
            "descripcion": "Tapiz decorativo hecho en telar por artesanos de Nariño.",
            "categoria": "Decoración para el Hogar",
            "stock": 6,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/tapiz.jpg"
          },
          {
            "id": 7,
            "nombre": "Lámpara de Totumo Tallada",
            "precio": 65000,
            "descripcion": "Lámpara artesanal hecha con totumo tallado a mano.",
            "categoria": "Decoración para el Hogar",
            "stock": 9,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/lampara.jpg"
          },
          {
            "id": 8,
            "nombre": "Cuadro Pintado en Madera",
            "precio": 72000,
            "descripcion": "Cuadro decorativo pintado sobre madera recuperada.",
            "categoria": "Decoración para el Hogar",
            "stock": 4,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/cuadroMadera.jpg"
          },
          {
            "id": 9,
            "nombre": "Sombrero Vueltiao",
            "precio": 150000,
            "descripcion": "Sombrero tradicional hecho con caña flecha por artesanos Zenú.",
            "categoria": "Arte y Recuerdos",
            "stock": 8,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/sombrero.jpg"
          },
          {
            "id": 10,
            "nombre": "Máscara Indígena Embera",
            "precio": 130000,
            "descripcion": "Máscara artesanal pintada a mano con símbolos Embera.",
            "categoria": "Arte y Recuerdos",
            "stock": 4,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/mascara.jpg"
          },
          {
            "id": 11,
            "nombre": "Cuadro Abstracto Decorativo",
            "precio": 60000,
            "descripcion": "Cuadro pintado a mano con motivos abstractos, ideal para decorar cualquier espacio",
            "categoria": "Arte y Recuerdos",
            "stock": 10,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/cuadroAbstracto.jpg"
          },
          {
            "id": 12,
            "nombre": "Cuenco Decorativo Zenú",
            "precio": 40000,
            "descripcion": "Cuenco elaborado con fibras y patrones tradicionales Zenú.",
            "categoria": "Arte y Recuerdos",
            "stock": 14,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/cuenco.jpg"
          },
          {
            "id": 13,
            "nombre": "Tazón de Cerámica Negra",
            "precio": 45000,
            "descripcion": "Tazón artesanal elaborado en cerámica negra de La Chamba.",
            "categoria": "Utensilios Funcionales",
            "stock": 20,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/tazon.jpg"
          },
          {
            "id": 14,
            "nombre": "Plato de Madera de Nogal",
            "precio": 55000,
            "descripcion": "Plato tallado a mano en madera de nogal.",
            "categoria": "Utensilios Funcionales",
            "stock": 18,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/plato.jpg"
          },
          {
            "id": 15,
            "nombre": "Cuchara de Guadua",
            "precio": 15000,
            "descripcion": "Cuchara artesanal hecha con guadua colombiana.",
            "categoria": "Utensilios Funcionales",
            "stock": 30,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/cuchara.jpg"
          },
          {
            "id": 16,
            "nombre": "Jarra de Cerámica de Ráquira",
            "precio": 48000,
            "descripcion": "Jarra artesanal hecha en Ráquira con diseños tradicionales.",
            "categoria": "Utensilios Funcionales",
            "stock": 11,
            "imagen": "backend/src/main/resources/static/IMG/imgProductos/jarron.jpg"
          }
        ]
        """;

    try {
      List<Map<String, Object>> productosJson = objectMapper.readValue(json, new TypeReference<>() {
      });

      for (Map<String, Object> map : productosJson) {
        producto p = new producto();
        p.setNombreProducto((String) map.get("nombre"));
        p.setPrecioProducto(((Number) map.get("precio")).floatValue());
        p.setDescripcionProducto((String) map.get("descripcion"));
        p.setCategoriaProducto((String) map.get("categoria"));
        p.setStockProducto(((Number) map.get("stock")).intValue());
        p.setImagenProducto((String) map.get("imagen"));

        productoRepository.save(p);
      }
      System.out.println("Productos cargados exitosamente.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
