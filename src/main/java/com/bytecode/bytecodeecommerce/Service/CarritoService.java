package com.bytecode.bytecodeecommerce.Service;

import com.bytecode.bytecodeecommerce.models.ItemCarrito;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface CarritoService {

    void agregarProductoAlCarrito(Long productoId, int cantidad, HttpSession session);

    void agregarProductoAlCarrito(Long productoId, int cantidad);

    void eliminarProductoDelCarrito(Long productoId, HttpSession session);

    void eliminarProductoDelCarrito(Long productoId);

    List<ItemCarrito> obtenerCarrito(HttpSession session);

    List<ItemCarrito> obtenerCarrito();
}

