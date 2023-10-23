package com.bytecode.bytecodeecommerce.Service;


import com.bytecode.bytecodeecommerce.models.MetodoPago;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MetodoPagoService {
    Page<MetodoPago> obtenerTodosMetodosPago(Pageable pageable);
    MetodoPago obtenerMetodoPagoPorId(int id);
    void guardarMetodoPago(MetodoPago metodoPago);
    void eliminarMetodoPago(int id);
}
