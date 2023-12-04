package com.bytecode.bytecodeecommerce.Service.Impl;

import com.bytecode.bytecodeecommerce.Repository.DescuentoRepository;
import com.bytecode.bytecodeecommerce.Service.DescuentoService;
import com.bytecode.bytecodeecommerce.models.Descuento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional  // Esta anotación hace que todos los métodos de esta clase sean transaccionales
public class DescuentoServiceImpl implements DescuentoService {

    private final DescuentoRepository descuentoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Descuento> obtenerTodosDescuentos() {
        return descuentoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Descuento obtenerDescuentoPorId(int id) {
        return descuentoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardarDescuento(Descuento descuento) {
        descuentoRepository.save(descuento);
    }

    @Override
    @Transactional
    public void eliminarDescuento(int id) {
        descuentoRepository.deleteById(id);
    }
}
