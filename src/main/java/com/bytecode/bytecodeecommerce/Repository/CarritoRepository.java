package com.bytecode.bytecodeecommerce.Repository;

import com.bytecode.bytecodeecommerce.models.CarritoCompras;
import com.bytecode.bytecodeecommerce.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarritoRepository extends JpaRepository<CarritoCompras, Long> {

    Optional<CarritoCompras> findByCliente(Cliente cliente);
}

