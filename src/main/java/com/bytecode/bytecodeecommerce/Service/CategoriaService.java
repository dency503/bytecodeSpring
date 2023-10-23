package com.bytecode.bytecodeecommerce.Service;

import com.bytecode.bytecodeecommerce.Repository.CategoriaRepository;
import com.bytecode.bytecodeecommerce.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



public interface CategoriaService {
    List<Categoria> obtenerTodasCategorias();
    Categoria obtenerCategoriaPorId(int id);
    void guardarCategoria(Categoria categoria);
    void eliminarCategoria(int id);
}
