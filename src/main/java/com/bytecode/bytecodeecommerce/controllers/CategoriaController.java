package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Service.CategoriaService;
import com.bytecode.bytecodeecommerce.models.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> obtenerTodasCategorias() {
        return categoriaService.obtenerTodasCategorias();
    }

    @GetMapping("/{id}")
    public Categoria obtenerCategoriaPorId(@PathVariable int id) {
        return categoriaService.obtenerCategoriaPorId(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void guardarCategoria(@RequestBody Categoria categoria) {
        categoriaService.guardarCategoria(categoria);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public void actualizarCategoria(@PathVariable int id, @RequestBody Categoria categoria) {
        Categoria existingCategoria = categoriaService.obtenerCategoriaPorId(id);
        if (existingCategoria != null) {
            existingCategoria.setNombreCategoria(categoria.getNombreCategoria());
            categoriaService.guardarCategoria(existingCategoria);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable int id) {
        categoriaService.eliminarCategoria(id);
    }
}
