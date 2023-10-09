package com.bytecode.bytecodeecommerce.Service.Impl;

import com.bytecode.bytecodeecommerce.Repository.UserRepository;
import com.bytecode.bytecodeecommerce.Service.AuthenticationService;
import com.bytecode.bytecodeecommerce.Service.JwtService;
import com.bytecode.bytecodeecommerce.dao.request.SignUpRequest;
import com.bytecode.bytecodeecommerce.dao.request.SigninRequest;
import com.bytecode.bytecodeecommerce.dao.response.JwtAuthenticationResponse;
import com.bytecode.bytecodeecommerce.models.Role;
import com.bytecode.bytecodeecommerce.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = Usuario.builder().username(request.getUsername())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
            System.out.println("el usuario es" +user);
            var jwt = jwtService.generateToken(user);

            return JwtAuthenticationResponse.builder().token(jwt).build();
        } catch (AuthenticationException e) {
            // Manejar la excepción de autenticación aquí
            // Por ejemplo, puedes devolver un mensaje de error personalizado

            throw new RuntimeException("Error de autenticación: " + e.getMessage());
        }
    }
}
