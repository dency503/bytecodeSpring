package com.bytecode.bytecodeecommerce.Service.Impl;


import com.bytecode.bytecodeecommerce.Repository.MetodoPagoRepository;
import com.bytecode.bytecodeecommerce.Service.MetodoPagoService;
import com.bytecode.bytecodeecommerce.models.MetodoPago;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;



    @Override
    @Transactional(readOnly = true)
    public Page<MetodoPago> obtenerTodosMetodosPago(Pageable pageable) {
        return metodoPagoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public MetodoPago obtenerMetodoPagoPorId(int id) {
        return metodoPagoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardarMetodoPago(MetodoPago metodoPago) {
        metodoPagoRepository.save(metodoPago);
    }

    @Override
    @Transactional
    public void eliminarMetodoPago(int id) {
        metodoPagoRepository.deleteById(id);
    }
}
