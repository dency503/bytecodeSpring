package com.bytecode.bytecodeecommerce.Repository;

import com.bytecode.bytecodeecommerce.models.DetalleVenta;
import com.bytecode.bytecodeecommerce.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Integer> {
}
