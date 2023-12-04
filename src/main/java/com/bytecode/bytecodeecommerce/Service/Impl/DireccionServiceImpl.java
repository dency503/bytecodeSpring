package com.bytecode.bytecodeecommerce.Service.Impl;



import com.bytecode.bytecodeecommerce.Repository.DireccionRepository;
import com.bytecode.bytecodeecommerce.Service.DireccionService;
import com.bytecode.bytecodeecommerce.models.Direccion;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DireccionServiceImpl implements DireccionService {

    private final DireccionRepository direccionRepository;

    @Override
    @Transactional(readOnly = true)
    public Direccion obtenerDireccionPorId(int idDireccion) {
        return direccionRepository.findById(idDireccion)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Direccion> obtenerDirecciones(Pageable pageable) {
        return direccionRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Direccion guardarDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    @Override
    @Transactional
    public Direccion modificarDireccion(int idDireccion, Direccion nuevaDireccion) {
        Direccion direccion = obtenerDireccionPorId(idDireccion);
        direccion.setLinea1(nuevaDireccion.getLinea1());
        direccion.setDistrito(nuevaDireccion.getDistrito());
        direccion.setCodigoPostal(nuevaDireccion.getCodigoPostal());
        direccion.setPais(nuevaDireccion.getPais());
        return direccionRepository.save(direccion);
    }

    @Override
    @Transactional
    public void eliminarDireccion(int idDireccion) {
        direccionRepository.deleteById(idDireccion);
    }
}
