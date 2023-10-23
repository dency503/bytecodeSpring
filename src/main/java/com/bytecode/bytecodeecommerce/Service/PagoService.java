package com.bytecode.bytecodeecommerce.Service;

import com.bytecode.bytecodeecommerce.models.Pago;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PagoService {
    Page<Pago> obtenerTodosPagos(Pageable pageable);
    Pago obtenerPagoPorId(int id);
    void guardarPago(Pago pago);
    void eliminarPago(int id);
}
