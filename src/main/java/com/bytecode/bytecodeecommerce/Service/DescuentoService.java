package com.bytecode.bytecodeecommerce.Service;


import com.bytecode.bytecodeecommerce.models.Descuento;

import java.util.List;

public interface DescuentoService {
    List<Descuento> obtenerTodosDescuentos();
    Descuento obtenerDescuentoPorId(int id);
    void guardarDescuento(Descuento descuento);
    void eliminarDescuento(int id);
}