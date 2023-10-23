package com.bytecode.bytecodeecommerce.Service.Impl;


import com.bytecode.bytecodeecommerce.Repository.ProductoRepository;
import com.bytecode.bytecodeecommerce.Service.ProductoService;
import com.bytecode.bytecodeecommerce.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerTodosProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
