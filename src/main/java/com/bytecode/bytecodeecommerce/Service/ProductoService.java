package com.bytecode.bytecodeecommerce.Service;

import com.bytecode.bytecodeecommerce.models.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> obtenerTodosProductos();
    Producto obtenerProductoPorId(Long id);
    void guardarProducto(Producto producto);
    void eliminarProducto(Long id);
}
