package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Service.DepartamentoService;
import com.bytecode.bytecodeecommerce.models.Departamento;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<Page<Departamento>> getAllDepartamentos(Pageable pageable) {
        Page<Departamento> departamentos = departamentoService.getAllDepartamentos(pageable);
        return new ResponseEntity<>(departamentos, HttpStatus.OK);
    }

    @GetMapping("/{idDepartamento}")
    public ResponseEntity<Departamento> getDepartamentoById(@PathVariable String idDepartamento) {
        Optional<Departamento> departamento = departamentoService.getDepartamentoById(idDepartamento);
        return departamento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Departamento> createDepartamento(@RequestBody Departamento departamento) {
        Departamento createdDepartamento = departamentoService.saveDepartamento(departamento);
        return new ResponseEntity<>(createdDepartamento, HttpStatus.CREATED);
    }

    @PutMapping("/{idDepartamento}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Departamento> updateDepartamento(
            @PathVariable String idDepartamento,
            @RequestBody Departamento departamento) {
        if (!departamentoService.getDepartamentoById(idDepartamento).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departamento.setIdDepartamento(idDepartamento);
        Departamento updatedDepartamento = departamentoService.saveDepartamento(departamento);
        return new ResponseEntity<>(updatedDepartamento, HttpStatus.OK);
    }

    @DeleteMapping("/{idDepartamento}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable String idDepartamento) {
        if (!departamentoService.getDepartamentoById(idDepartamento).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departamentoService.deleteDepartamento(idDepartamento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}