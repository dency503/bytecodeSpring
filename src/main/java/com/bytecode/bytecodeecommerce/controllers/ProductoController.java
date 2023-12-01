package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Service.ProductoService;
import com.bytecode.bytecodeecommerce.models.Producto;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;



    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosProductos() {
        List<Producto> productos = productoService.obtenerTodosProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        return producto != null ?
                new ResponseEntity<>(producto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> guardarProducto(@RequestBody Producto producto) {
        productoService.guardarProducto(producto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto existingProducto = productoService.obtenerProductoPorId(id);
        if (existingProducto != null) {
            existingProducto.setNombreProducto(producto.getNombreProducto());
            existingProducto.setImagenURl(producto.getImagenURl());
            existingProducto.setDescripcionProducto(producto.getDescripcionProducto());
            existingProducto.setPrecio(producto.getPrecio());
            existingProducto.setStock(producto.getStock());
            productoService.guardarProducto(existingProducto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (productoService.obtenerProductoPorId(id) != null) {
            productoService.eliminarProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<Page<Producto>> getProductosPorCategoriaId(@PathVariable int categoriaId, Pageable pageable) {
        Page<Producto> productos = productoService.getProductosByCategoriaId(categoriaId, pageable);

        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Puedes ajustar el HttpStatus según tus necesidades
        } else {
            return new ResponseEntity<>(productos, HttpStatus.OK);
        }
    }
    @GetMapping("/buscar")
    public ResponseEntity<Page<Producto>> buscarProductosPorTermino(
            @RequestParam String termino,
            Pageable pageable) {

        Page<Producto> productos = productoService.searchProductosPaginados(termino, pageable);

        return ResponseEntity.ok(productos);
    }



}

