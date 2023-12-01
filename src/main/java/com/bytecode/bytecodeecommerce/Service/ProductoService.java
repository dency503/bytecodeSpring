package com.bytecode.bytecodeecommerce.Service;

import com.bytecode.bytecodeecommerce.models.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductoService {
    List<Producto> obtenerTodosProductos();
    Producto obtenerProductoPorId(Long id);
    void guardarProducto(Producto producto);
    void eliminarProducto(Long id);

    Page<Producto> getProductosByCategoriaId(int categoriaId, Pageable pageable);

    Page<Producto> searchProductosPaginados(String termino, Pageable pageable);
}
