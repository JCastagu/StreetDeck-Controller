package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;
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
// Documentación de la API con Swagger
@Tag(name = "Productos", description = "Operaciones relacionadas con los productos de StreetDeck")
public class ProductoController {

    // 1. GET con @RequestParam: Filtra por categoría de skate
    @GetMapping
    public Map<String, Object> listarProductos(@RequestParam("categoria") String categoria) {
        List<String> categoriasFijas = List.of("tablas", "ruedas", "trucks", "rodamientos", "lijas");
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Catálogo de StreetDeck obtenido correctamente");
        respuesta.put("categoriaFiltro", categoria);
        respuesta.put("categoriasDisponibles", categoriasFijas);
        return respuesta;
    }

    // 2. GET con @PathVariable y @RequestHeader: Busca producto de skate por ID y valida token
    @GetMapping("/{id}")
    public Map<String, Object> obtenerProductoPorId(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String token) {
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("idProducto", id);
        respuesta.put("producto", "Tabla Maple 8.0 StreetDeck");
        respuesta.put("mensaje", "Acceso concedido al producto de skate");
        respuesta.put("tokenRecibido", token);
        return respuesta;
    }

    // 3. POST con @RequestBody y @RequestHeader: Registra un nuevo producto en StreetDeck
    @PostMapping
    public Map<String, Object> crearProducto(
            @RequestBody Map<String, Object> producto,
            @RequestHeader("X-User-Role") String rol) {
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Producto agregado con éxito a StreetDeck");
        respuesta.put("datosProducto", producto);
        respuesta.put("rolUsuario", rol);
        return respuesta;
    }
}
