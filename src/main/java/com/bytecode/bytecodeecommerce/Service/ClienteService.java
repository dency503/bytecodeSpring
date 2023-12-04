package com.bytecode.bytecodeecommerce.Service;



import com.bytecode.bytecodeecommerce.models.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClienteService {


    @Transactional(readOnly = true)
    Page<Cliente> obtenerTodosClientes(Pageable pageable);

    Cliente obtenerClientePorId(Long id);
    void guardarCliente(Cliente cliente);
    void eliminarCliente(Long id);
}
