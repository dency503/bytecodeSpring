package com.bytecode.bytecodeecommerce.Service.Impl;

import com.bytecode.bytecodeecommerce.Repository.ProductoRepository;
import com.bytecode.bytecodeecommerce.Service.CarritoService;
import com.bytecode.bytecodeecommerce.models.ItemCarrito;
import com.bytecode.bytecodeecommerce.models.Producto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoServiceImpl implements CarritoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public CarritoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    @Override
    public void agregarProductoAlCarrito(Long productoId, int cantidad, HttpSession session) {

    }

    @Override
    public void agregarProductoAlCarrito(Long productoId, int cantidad) {

    }

    @Override
    public void eliminarProductoDelCarrito(Long productoId, HttpSession session) {

    }

    @Override
    public void eliminarProductoDelCarrito(Long productoId) {

    }

    @Override
    public List<ItemCarrito> obtenerCarrito(HttpSession session) {
        return null;
    }

    @Override
    public List<ItemCarrito> obtenerCarrito() {
        return null;
    }
}
