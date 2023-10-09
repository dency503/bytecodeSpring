package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Service.ProductoService;
import com.bytecode.bytecodeecommerce.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productos")  // La ruta base para todos los endpoints relacionados con productos
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{id}")  // Define la ruta para este endpoint, incluyendo un parámetro de ID en la URL
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }

    @GetMapping()  // Define la ruta para este endpoint, incluyendo un parámetro de ID en la URL
    public ResponseEntity<List<Producto>> todosProductos() {
        return productoService.all();
    }
}
