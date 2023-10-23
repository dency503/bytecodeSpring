package com.bytecode.bytecodeecommerce.Service.Impl;



import com.bytecode.bytecodeecommerce.Repository.MarcaRepository;
import com.bytecode.bytecodeecommerce.Service.MarcaService;
import com.bytecode.bytecodeecommerce.models.Marca;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository marcaRepository;



    @Override
    @Transactional(readOnly = true)
    public List<Marca> obtenerTodasMarcas() {
        return marcaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Marca obtenerMarcaPorId(int id) {
        return marcaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardarMarca(Marca marca) {
        marcaRepository.save(marca);
    }

    @Override
    @Transactional
    public void eliminarMarca(int id) {
        marcaRepository.deleteById(id);
    }
}
