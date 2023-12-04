package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Service.CargoService;
import com.bytecode.bytecodeecommerce.models.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    private final CargoService cargoService;

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping
    public ResponseEntity<Page<Cargo>> obtenerTodosCargos(Pageable pageable) {
        try {
            Page<Cargo> cargos = cargoService.obtenerTodosCargos(pageable);
            return new ResponseEntity<>(cargos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> obtenerCargoPorId(@PathVariable int id) {
        try {
            Cargo cargo = cargoService.obtenerCargoPorId(id);
            if (cargo != null) {
                return new ResponseEntity<>(cargo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> guardarCargo(@RequestBody Cargo cargo) {
        try {
            cargoService.guardarCargo(cargo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarCargo(@PathVariable int id, @RequestBody Cargo cargo) {
        try {
            Cargo existingCargo = cargoService.obtenerCargoPorId(id);
            if (existingCargo != null) {
                existingCargo.setNombreCargo(cargo.getNombreCargo());

                // También puedes manejar otros campos aquí según tus necesidades
                cargoService.guardarCargo(existingCargo);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCargo(@PathVariable int id) {
        try {
            cargoService.eliminarCargo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
