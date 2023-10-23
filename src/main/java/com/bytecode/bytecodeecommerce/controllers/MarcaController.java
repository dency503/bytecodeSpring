package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Service.MarcaService;
import com.bytecode.bytecodeecommerce.models.Marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@Validated
public class MarcaController {

    private final MarcaService marcaService;

    @Autowired
    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<Marca>> obtenerTodasMarcas() {
        List<Marca> marcas = marcaService.obtenerTodasMarcas();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> obtenerMarcaPorId(@PathVariable int id) {
        Marca marca = marcaService.obtenerMarcaPorId(id);
        return marca != null ?
                new ResponseEntity<>(marca, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> guardarMarca(@Valid @RequestBody Marca marca) {
        marcaService.guardarMarca(marca);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> actualizarMarca(@PathVariable int id, @Valid @RequestBody Marca marca) {
        Marca existingMarca = marcaService.obtenerMarcaPorId(id);
        if (existingMarca != null) {
            existingMarca.setNombreMarca(marca.getNombreMarca());
            // También puedes manejar otras propiedades y relaciones aquí según tus necesidades
            marcaService.guardarMarca(existingMarca);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> eliminarMarca(@PathVariable int id) {
        if (marcaService.obtenerMarcaPorId(id) != null) {
            marcaService.eliminarMarca(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
