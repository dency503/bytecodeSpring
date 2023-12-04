package com.bytecode.bytecodeecommerce.Service.Impl;


import com.bytecode.bytecodeecommerce.Repository.DistritoRepository;
import com.bytecode.bytecodeecommerce.Service.DistritoService;
import com.bytecode.bytecodeecommerce.models.Distrito;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DistritoServiceImpl implements DistritoService {

    private final DistritoRepository distritoRepository;

    @Override
    @Transactional(readOnly = true)
    public Distrito obtenerDistritoPorId(String idDistrito) {
        return distritoRepository.findById(idDistrito)
                .orElseThrow(() -> new RuntimeException("Distrito no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Distrito> obtenerDistritos(Pageable pageable) {
        return distritoRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Distrito guardarDistrito(Distrito distrito) {
        return distritoRepository.save(distrito);
    }

    @Override
    @Transactional
    public Distrito modificarDistrito(String idDistrito, Distrito nuevoDistrito) {
        Distrito distrito = obtenerDistritoPorId(idDistrito);
        distrito.setDistrito(nuevoDistrito.getDistrito());
        distrito.setMunicipio(nuevoDistrito.getMunicipio());
        return distritoRepository.save(distrito);
    }

    @Override
    @Transactional
    public void eliminarDistrito(String idDistrito) {
        distritoRepository.deleteById(idDistrito);
    }
}
