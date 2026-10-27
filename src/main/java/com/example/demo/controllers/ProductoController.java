package com.streetdeck.controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    // 1. GET con @RequestParam: Filtra por categoría de skate
    @GetMapping
    public Map<String, Object> listarProductos(@RequestParam("categoria") String categoria) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Catálogo de StreetDeck obtenido correctamente");
        respuesta.put("categoriaFiltro", categoria);
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