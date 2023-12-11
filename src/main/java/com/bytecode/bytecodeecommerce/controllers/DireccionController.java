package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Service.DireccionService;
import com.bytecode.bytecodeecommerce.models.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    private final DireccionService direccionService;

    @Autowired
    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Direccion obtenerDireccionPorId(@PathVariable int id) {
        return direccionService.obtenerDireccionPorId(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public Direccion guardarDireccion(@RequestBody Direccion direccion) {
        return direccionService.guardarDireccion(direccion);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Direccion modificarDireccion(@PathVariable int id, @RequestBody Direccion nuevaDireccion) {
        return direccionService.modificarDireccion(id, nuevaDireccion);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/user")
    public Direccion modificarUsuarioDireccion(Authentication authentication, @RequestBody Direccion nuevaDireccion) {
        return direccionService.modificarUsuarioDireccion(authentication, nuevaDireccion);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminarDireccion(@PathVariable int id) {
        direccionService.eliminarDireccion(id);
    }
}
