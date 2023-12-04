package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Service.DistritoService;
import com.bytecode.bytecodeecommerce.models.Distrito;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/distritos")
@RequiredArgsConstructor
public class DistritoController {

    private final DistritoService distritoService;

    @GetMapping("/{idDistrito}")
    public ResponseEntity<Distrito> obtenerDistritoPorId(@PathVariable String idDistrito) {
        Distrito distrito = distritoService.obtenerDistritoPorId(idDistrito);
        return new ResponseEntity<>(distrito, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Distrito>> obtenerDistritos(Pageable pageable) {
        Page<Distrito> distritos = distritoService.obtenerDistritos(pageable);
        return new ResponseEntity<>(distritos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Distrito> guardarDistrito(@RequestBody Distrito distrito) {
        Distrito nuevoDistrito = distritoService.guardarDistrito(distrito);
        return new ResponseEntity<>(nuevoDistrito, HttpStatus.CREATED);
    }

    @PutMapping("/{idDistrito}")
    public ResponseEntity<Distrito> modificarDistrito(@PathVariable String idDistrito, @RequestBody Distrito nuevoDistrito) {
        Distrito distritoModificado = distritoService.modificarDistrito(idDistrito, nuevoDistrito);
        return new ResponseEntity<>(distritoModificado, HttpStatus.OK);
    }

    @DeleteMapping("/{idDistrito}")
    public ResponseEntity<Void> eliminarDistrito(@PathVariable String idDistrito) {
        distritoService.eliminarDistrito(idDistrito);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
