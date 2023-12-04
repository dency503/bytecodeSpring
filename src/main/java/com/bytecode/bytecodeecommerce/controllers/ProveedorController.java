package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Service.ProveedorService;
import com.bytecode.bytecodeecommerce.models.Proveedor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;



    @GetMapping
    public ResponseEntity<Page<Proveedor>> obtenerTodosProveedores(Pageable pageable) {
        return proveedorService.obtenerTodosProveedores(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable Long id) {
        return proveedorService.obtenerProveedorPorId(id);
    }

    @PostMapping
    public ResponseEntity<Void> guardarProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.guardarProveedor(proveedor);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> modificarProveedor(@PathVariable Long id,@RequestBody Proveedor proveedor) {
        return proveedorService.modificarProveedor(id,proveedor);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        return proveedorService.eliminarProveedor(id);
    }
}