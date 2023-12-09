package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Service.VentaService;
import com.bytecode.bytecodeecommerce.models.Usuario;
import com.bytecode.bytecodeecommerce.models.Venta;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;


    @GetMapping("/all")

    public ResponseEntity<Page<Venta>> obtenerVentas(Pageable pageable) {
        Page<Venta> ventas = ventaService.obtenerVentas(pageable);
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Venta>> obtenerVentasUsuario(Pageable pageable,Authentication authentication) {

        if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
            // Solo continuamos si authentication no es nulo y su principal es una instancia de Usuario
            Page<Venta> ventas = ventaService.obtenerVentasUsuario(pageable, authentication);
            return new ResponseEntity<>(ventas, HttpStatus.OK);
        } else {
            // Manejar el caso en que authentication es nulo o no contiene un principal de tipo Usuario
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Otra respuesta adecuada según tus requisitos
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{ventaId}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable int ventaId) {
        Venta venta = ventaService.obtenerVentaPorId(ventaId);
        return new ResponseEntity<>(venta, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Venta> guardarVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.guardarVenta(venta);
        return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{ventaId}")
    public ResponseEntity<Venta> modificarVenta(@PathVariable int ventaId, @RequestBody Venta nuevaVenta) {
        Venta ventaModificada = ventaService.modificarVenta(ventaId, nuevaVenta);
        return new ResponseEntity<>(ventaModificada, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{ventaId}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable int ventaId) {
        ventaService.eliminarVenta(ventaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
