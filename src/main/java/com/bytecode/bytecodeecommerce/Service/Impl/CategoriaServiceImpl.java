package com.bytecode.bytecodeecommerce.Service.Impl;

import com.bytecode.bytecodeecommerce.Repository.CategoriaRepository;
import com.bytecode.bytecodeecommerce.Service.CategoriaService;
import com.bytecode.bytecodeecommerce.models.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> obtenerTodasCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria obtenerCategoriaPorId(int id) {
        return categoriaRepository.findById(id).orElse(null);
    }



    @Override
    @Transactional
    public void guardarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public void eliminarCategoria(int id) {
        categoriaRepository.deleteById(id);
    }
}