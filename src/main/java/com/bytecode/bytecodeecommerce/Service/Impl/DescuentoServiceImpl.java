package com.bytecode.bytecodeecommerce.Service.Impl;


import com.bytecode.bytecodeecommerce.Repository.DescuentoRepository;
import com.bytecode.bytecodeecommerce.Service.DescuentoService;
import com.bytecode.bytecodeecommerce.models.Descuento;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DescuentoServiceImpl implements DescuentoService {

    private final DescuentoRepository descuentoRepository;


    @Override
    public List<Descuento> obtenerTodosDescuentos() {
        return descuentoRepository.findAll();
    }

    @Override
    public Descuento obtenerDescuentoPorId(int id) {
        return descuentoRepository.findById(id).orElse(null);
    }


    @Override
    public void guardarDescuento(Descuento descuento) {
        descuentoRepository.save(descuento);
    }

    @Override
    public void eliminarDescuento(int id) {
        descuentoRepository.deleteById(id);
    }
}
