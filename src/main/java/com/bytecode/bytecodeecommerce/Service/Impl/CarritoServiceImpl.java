package com.bytecode.bytecodeecommerce.Service.Impl;

import com.bytecode.bytecodeecommerce.Repository.ProductoRepository;
import com.bytecode.bytecodeecommerce.Service.CarritoService;
import com.bytecode.bytecodeecommerce.models.ItemCarrito;
import com.bytecode.bytecodeecommerce.models.Producto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional  // Esta anotación hace que todos los métodos de esta clase sean transaccionales
@RequiredArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private final ProductoRepository productoRepository;



    @Override
    @Transactional  // Esta anotación hace que este método sea transaccional
    public void agregarProductoAlCarrito(Long productoId, int cantidad, HttpSession session) {
        // Lógica para agregar producto al carrito
    }

    @Override
    @Transactional
    public void agregarProductoAlCarrito(Long productoId, int cantidad) {
        // Lógica para agregar producto al carrito
    }

    @Override
    @Transactional
    public void eliminarProductoDelCarrito(Long productoId, HttpSession session) {
        // Lógica para eliminar producto del carrito
    }

    @Override
    @Transactional
    public void eliminarProductoDelCarrito(Long productoId) {
        // Lógica para eliminar producto del carrito
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemCarrito> obtenerCarrito(HttpSession session) {
        // Lógica para obtener el carrito
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemCarrito> obtenerCarrito() {
        // Lógica para obtener el carrito
        return null;
    }
}
