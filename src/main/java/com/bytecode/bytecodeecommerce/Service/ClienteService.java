package com.bytecode.bytecodeecommerce.Service;



import com.bytecode.bytecodeecommerce.models.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> obtenerTodosClientes();
    Cliente obtenerClientePorId(Long id);
    void guardarCliente(Cliente cliente);
    void eliminarCliente(Long id);
}
