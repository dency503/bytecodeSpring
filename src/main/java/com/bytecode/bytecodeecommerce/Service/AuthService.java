package com.bytecode.bytecodeecommerce.Service;


import com.bytecode.bytecodeecommerce.Repository.ClienteRepository;
import com.bytecode.bytecodeecommerce.Repository.UserRepository;
import com.bytecode.bytecodeecommerce.models.Cliente;
import com.bytecode.bytecodeecommerce.models.Usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;



    public void registerNewUser(String username, String email, String password, String nombreCliente, String apellidoCliente, String telefono) {
        // Create Usuario
        Usuario usuario = Usuario.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        // Create Cliente
        Cliente cliente = new Cliente();
        cliente.setNombreCliente(nombreCliente);
        cliente.setApellidoCliente(apellidoCliente);
        cliente.setEmail(email);
        cliente.setTelefono(telefono);
        cliente.setFechaCreacion(new Date());

        // Associate Usuario with Cliente
        cliente.setUsuario(usuario);

        // Save Usuario and Cliente
        clienteRepository.save(cliente);
        usuarioRepository.save(usuario);

    }
}
