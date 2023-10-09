package com.bytecode.bytecodeecommerce.Service;

import com.bytecode.bytecodeecommerce.Repository.ProductoRepository;
import com.bytecode.bytecodeecommerce.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ResponseEntity<Producto>  obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null);

        if (producto != null) {
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<Producto>>  all() {
        List<Producto> producto = productoRepository.findAll();

        return ResponseEntity.ok(producto);
    }

    // Otros métodos de servicio relacionados con los productos
}
