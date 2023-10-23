package com.bytecode.bytecodeecommerce.controllers;



import com.bytecode.bytecodeecommerce.Service.CargoService;
import com.bytecode.bytecodeecommerce.models.Cargo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    private final CargoService cargoService;

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping
    public Page<Cargo> obtenerTodosCargos(Pageable pageable) {
        return cargoService.obtenerTodosCargos(pageable);
    }

    @GetMapping("/{id}")
    public Cargo obtenerCargoPorId(@PathVariable int id) {
        return cargoService.obtenerCargoPorId(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void guardarCargo(@RequestBody Cargo cargo) {
        cargoService.guardarCargo(cargo);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public void actualizarCargo(@PathVariable int id, @RequestBody Cargo cargo) {
        Cargo existingCargo = cargoService.obtenerCargoPorId(id);
        if (existingCargo != null) {
            existingCargo.setNombreCargo(cargo.getNombreCargo());
            existingCargo.setSalario(cargo.getSalario());
            // También puedes manejar otros campos aquí según tus necesidades
            cargoService.guardarCargo(existingCargo);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminarCargo(@PathVariable int id) {
        cargoService.eliminarCargo(id);
    }
}
