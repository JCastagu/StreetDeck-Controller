package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

// Importaciones de OpenAPI / Swagger para documentar la API
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "API para la gestión y consulta de productos de StreetDeck")
public class ProductoController {

    // 1. GET: Filtra por categoría con ejemplos reales
    @Operation(
        summary = "Obtener productos por categoría", 
        description = "Devuelve el catálogo de StreetDeck filtrado según la categoría (ej: tablas, ruedas) con marcas reales como Santa Cruz o Baker."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo obtenido exitosamente."),
        @ApiResponse(responseCode = "400", description = "Parámetro de categoría inválido")
    })
    @GetMapping
    public ResponseEntity<Map<String, Object>> listarProductos(
            @RequestParam("categoria") @Parameter(description = "Categoría de skate (ej: tablas, ruedas)", required = true) String categoria) {
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Catálogo filtrado correctamente para StreetDeck");
        respuesta.put("categoriaFiltro", categoria);

        if (categoria.equalsIgnoreCase("tablas")) {
            respuesta.put("resultados", List.of(
                Map.of("id", 1, "marca", "Santa Cruz", "modelo", "Screaming Hand 8.25", "precio", 245000),
                Map.of("id", 2, "marca", "Baker", "modelo", "Team Brand Logo 8.0", "precio", 189900)
            ));
        } else if (categoria.equalsIgnoreCase("ruedas")) {
            respuesta.put("resultados", List.of(
                Map.of("id", 3, "marca", "Spitfire", "modelo", "Formula Four 52mm", "precio", 145000)
            ));
        } else {
            respuesta.put("resultados", List.of("No hay productos específicos cargados de muestra para esta categoría, pero el filtro opera correctamente."));
        }

        return ResponseEntity.ok(respuesta);
    }

    // 2. GET: Busca producto por ID 
    @Operation(
        summary = "Buscar producto por ID", 
        description = "Busca un producto específico de StreetDeck mediante su identificador único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado correctamente."),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerProductoPorId(
            @PathVariable("id") @Parameter(description = "Identificador único del producto", required = true) Long id) {
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("idProducto", id);
        respuesta.put("marca", "Santa Cruz");
        respuesta.put("producto", "Tabla Screaming Hand 8.25");
        respuesta.put("precio", 245000);
        respuesta.put("mensaje", "Producto obtenido correctamente de StreetDeck");
        
        return ResponseEntity.ok(respuesta);
    }

    // 3. POST: Registra un nuevo producto
    @Operation(
        summary = "Registrar nuevo producto", 
        description = "Permite dar de alta un nuevo artículo en la tienda."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto registrado con éxito."),
        @ApiResponse(responseCode = "400", description = "Datos de entrada incorrectos")
    })
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearProducto(
            @RequestBody @Parameter(description = "Cuerpo con los datos del nuevo producto en formato JSON", required = true) Map<String, Object> producto) {
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Producto agregado con éxito al inventario de StreetDeck");
        respuesta.put("datosRegistrados", producto);
        
        return ResponseEntity.ok(respuesta);
    }

    // 4. PUT: Actualiza un producto existente
    @Operation(
        summary = "Actualizar producto", 
        description = "Permite modificar los datos o el precio de un producto existente en el inventario de StreetDeck."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "El producto a actualizar no existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarProducto(
            @PathVariable("id") @Parameter(description = "Identificador único del producto a actualizar", required = true) Long id,
            @RequestBody @Parameter(description = "Nuevos datos o precio del producto en JSON", required = true) Map<String, Object> productoActualizado) {
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Inventario actualizado correctamente en StreetDeck");
        respuesta.put("idActualizado", id);
        respuesta.put("cambiosAplicados", productoActualizado);
        
        return ResponseEntity.ok(respuesta);
    }

    // 5. DELETE: Elimina un producto por ID
    @Operation(
        summary = "Eliminar producto", 
        description = "Permite dar de baja un producto del catálogo de StreetDeck mediante su identificador único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "El producto a eliminar no existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarProducto(
            @PathVariable("id") @Parameter(description = "Identificador único del producto a eliminar", required = true) Long id) {
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Producto eliminado correctamente del sistema de StreetDeck");
        respuesta.put("idEliminado", id);
        
        return ResponseEntity.ok(respuesta);
    }
}