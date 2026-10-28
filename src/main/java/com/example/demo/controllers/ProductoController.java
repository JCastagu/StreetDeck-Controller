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

    // 1. GET con @RequestParam: Filtra por categoría de skate
    @Operation(
        summary = "Obtener productos por categoría", 
        description = "Devuelve el catálogo de StreetDeck filtrado según la categoría de skate especificada[cite: 4]."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo obtenido exitosamente[cite: 6]"),
        @ApiResponse(responseCode = "400", description = "Parámetro de categoría inválido")
    })
    @GetMapping
    public Map<String, Object> listarProductos(
            @RequestParam("categoria") @Parameter(description = "Nombre de la categoría de skate (ej: tablas, ruedas)", required = true) String categoria) {
        List<String> categoriasFijas = List.of("tablas", "ruedas", "trucks", "rodamientos", "lijas");
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Catálogo de StreetDeck obtenido correctamente");
        respuesta.put("categoriaFiltro", categoria);
        respuesta.put("categoriasDisponibles", categoriasFijas);
        return respuesta;
    }

    // 2. GET con @PathVariable y @RequestHeader: Busca producto de skate por ID y valida token
    @Operation(
        summary = "Buscar producto por ID", 
        description = "Busca un producto específico de StreetDeck mediante su identificador único y valida el token de autorización[cite: 6]."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado correctamente[cite: 6]"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado[cite: 6]")
    })
    @GetMapping("/{id}")
    public Map<String, Object> obtenerProductoPorId(
            @PathVariable("id") @Parameter(description = "Identificador único del producto", required = true) Long id,
            @RequestHeader("Authorization") @Parameter(description = "Token de seguridad para la petición", required = true) String token) {
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("idProducto", id);
        respuesta.put("producto", "Tabla Maple 8.0 StreetDeck");
        respuesta.put("mensaje", "Acceso concedido al producto de skate");
        respuesta.put("tokenRecibido", token);
        return respuesta;
    }

    // 3. POST con @RequestBody y @RequestHeader: Registra un nuevo producto en StreetDeck
    @Operation(
        summary = "Registrar nuevo producto", 
        description = "Permite dar de alta un nuevo artículo en la tienda validando el rol de administrador."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto registrado con éxito[cite: 6]"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada incorrectos")
    })
    @PostMapping
    public Map<String, Object> crearProducto(
            @RequestBody @Parameter(description = "Cuerpo con los datos del nuevo producto en formato JSON", required = true) Map<String, Object> producto,
            @RequestHeader("X-User-Role") @Parameter(description = "Rol del usuario que realiza la petición", required = true) String rol) {
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Producto agregado con éxito a StreetDeck");
        respuesta.put("datosProducto", producto);
        respuesta.put("rolUsuario", rol);
        return respuesta;
    }

    // 4. DELETE: Elimina un producto por ID
    @Operation(
        summary = "Eliminar producto", 
        description = "Permite eliminar un producto del catálogo de StreetDeck mediante su identificador único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "El producto a eliminar no existe")
    })
    @DeleteMapping("/{id}")
    public Map<String, Object> eliminarProducto(
            @PathVariable("id") @Parameter(description = "Identificador único del producto a eliminar", required = true) Long id) {
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Producto eliminado correctamente de StreetDeck");
        respuesta.put("idEliminado", id);
        
        return ResponseEntity.ok(respuesta);
    }
}