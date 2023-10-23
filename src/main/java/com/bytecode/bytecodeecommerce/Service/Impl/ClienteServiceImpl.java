package com.bytecode.bytecodeecommerce.Service.Impl;


import com.bytecode.bytecodeecommerce.Repository.ClienteRepository;
import com.bytecode.bytecodeecommerce.Service.ClienteService;
import com.bytecode.bytecodeecommerce.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> obtenerTodosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }



    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
