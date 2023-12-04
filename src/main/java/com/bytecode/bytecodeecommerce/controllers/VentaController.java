package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Service.VentaService;
import com.bytecode.bytecodeecommerce.models.Venta;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;


    @GetMapping
    public ResponseEntity<Page<Venta>> obtenerVentas(Pageable pageable) {
        Page<Venta> ventas = ventaService.obtenerVentas(pageable);
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping("/{ventaId}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable int ventaId) {
        Venta venta = ventaService.obtenerVentaPorId(ventaId);
        return new ResponseEntity<>(venta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Venta> guardarVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.guardarVenta(venta);
        return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
    }

    @PutMapping("/{ventaId}")
    public ResponseEntity<Venta> modificarVenta(@PathVariable int ventaId, @RequestBody Venta nuevaVenta) {
        Venta ventaModificada = ventaService.modificarVenta(ventaId, nuevaVenta);
        return new ResponseEntity<>(ventaModificada, HttpStatus.OK);
    }

    @DeleteMapping("/{ventaId}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable int ventaId) {
        ventaService.eliminarVenta(ventaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
