package com.bytecode.bytecodeecommerce.Repository;

import com.bytecode.bytecodeecommerce.models.Cliente;
import com.bytecode.bytecodeecommerce.models.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta,Integer> {
    Page<Venta> findByCliente(Cliente cliente, Pageable pageable);

    Page<Venta> findByClienteOrderByFechaVentaDesc(Cliente cliente, Pageable pageable);
}
