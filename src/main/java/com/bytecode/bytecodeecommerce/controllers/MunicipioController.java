package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.models.Municipio;
import com.bytecode.bytecodeecommerce.models.Departamento;
import com.bytecode.bytecodeecommerce.Service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/municipios")
public class MunicipioController {

    private final MunicipioService municipioService;

    @Autowired
    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @GetMapping
    public ResponseEntity<Page<Municipio>> getAllMunicipios(Pageable pageable) {
        Page<Municipio> municipios = municipioService.getAllMunicipios(pageable);
        return ResponseEntity.ok(municipios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Municipio> getMunicipioById(@PathVariable String id) {
        Municipio municipio = municipioService.getMunicipioById(id);

        if (municipio != null) {
            return ResponseEntity.ok(municipio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Municipio> createMunicipio(@RequestBody Municipio municipio) {
        Municipio createdMunicipio = municipioService.saveMunicipio(municipio);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMunicipio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Municipio> updateMunicipio(@PathVariable String id, @RequestBody Municipio updatedMunicipio) {
        Municipio updated = municipioService.updateMunicipio(id, updatedMunicipio);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMunicipio(@PathVariable String id) {
        municipioService.deleteMunicipio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/departamento/{departamentoId}")
    public ResponseEntity<Page<Municipio>> getMunicipiosByDepartamento(
            @PathVariable String departamentoId, Pageable pageable) {
        Departamento departamento = new Departamento();
        departamento.setIdDepartamento(departamentoId);

        Page<Municipio> municipios = municipioService.getMunicipiosByDepartamento(departamento, pageable);
        return ResponseEntity.ok(municipios);
    }
}
