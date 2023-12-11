package com.bytecode.bytecodeecommerce.Repository;


import com.bytecode.bytecodeecommerce.models.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {


    Optional<ItemCarrito> findByProducto_ProductoId(Long id);

    Optional<ItemCarrito> findByProducto_ProductoIdAndCarritoCompras_carritoId(Long id, int carritoId);
}

