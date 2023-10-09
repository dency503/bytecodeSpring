package com.bytecode.bytecodeecommerce.Repository;

import com.bytecode.bytecodeecommerce.models.Cliente;
import com.bytecode.bytecodeecommerce.models.Producto;
import com.bytecode.bytecodeecommerce.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByUsuario(Usuario usuario);
}

