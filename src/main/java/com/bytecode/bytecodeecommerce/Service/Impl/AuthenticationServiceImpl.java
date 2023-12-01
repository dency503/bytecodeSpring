package com.bytecode.bytecodeecommerce.Service.Impl;

import com.bytecode.bytecodeecommerce.Repository.ClienteRepository;
import com.bytecode.bytecodeecommerce.Repository.UserRepository;
import com.bytecode.bytecodeecommerce.Service.AuthenticationService;
import com.bytecode.bytecodeecommerce.Service.JwtService;
import com.bytecode.bytecodeecommerce.dao.request.SignUpRequest;
import com.bytecode.bytecodeecommerce.dao.request.SigninRequest;
import com.bytecode.bytecodeecommerce.dao.response.JwtAuthenticationResponse;
import com.bytecode.bytecodeecommerce.models.Cliente;
import com.bytecode.bytecodeecommerce.models.Role;
import com.bytecode.bytecodeecommerce.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
// Otros imports

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        // Verifica si ya existe un usuario con el mismo nombre de usuario o email
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }



        // Si no hay conflictos, crea y guarda el usuario
        var user = Usuario.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        Cliente cliente = new Cliente();
        cliente.setNombreCliente(request.getNombreCliente());
        cliente.setApellidoCliente(request.getApellidoCliente());
        cliente.setEmail(request.getEmail());
        cliente.setTelefono(request.getTelefono());
        cliente.setFechaCreacion(new Date());

        // Associate Usuario with Cliente
        cliente.setUsuario(user);

        // Save Usuario and Cliente

        userRepository.save(user);
        clienteRepository.save(cliente);
        // Crea y devuelve el token de autenticación
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    @Override
    @Transactional
    public JwtAuthenticationResponse signin(SigninRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

            var jwt = jwtService.generateToken(user);

            return JwtAuthenticationResponse.builder().token(jwt).build();
        } catch (AuthenticationException e) {
            // Manejar la excepción de autenticación aquí
            // Por ejemplo, puedes devolver un mensaje de error personalizado

            throw new RuntimeException("Error de autenticación: " + e.getMessage());
        }
    }
}
