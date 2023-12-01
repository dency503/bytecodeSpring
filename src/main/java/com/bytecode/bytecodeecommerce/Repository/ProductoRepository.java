package com.bytecode.bytecodeecommerce.Repository;

import com.bytecode.bytecodeecommerce.models.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Métodos personalizados para consultas específicas si es necesario
    Page<Producto> findByCategoriaCategoriaId(int categoriaId, Pageable pageable);

    @Query("SELECT p FROM Producto p WHERE p.nombreProducto LIKE %:searchTerm%")
    Page<Producto> searchProductosPaginados(@Param("searchTerm") String searchTerm, Pageable pageable);
}

