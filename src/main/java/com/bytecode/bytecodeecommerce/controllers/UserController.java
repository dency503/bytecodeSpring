package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Repository.ClienteRepository;
import com.bytecode.bytecodeecommerce.Service.AuthenticationService;
import com.bytecode.bytecodeecommerce.models.Cliente;
import com.bytecode.bytecodeecommerce.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final ClienteRepository clienteRepository;
    private final AuthenticationService authenticationService;

    @GetMapping("/username")
    public String username(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails instanceof Usuario) {
            Usuario user = (Usuario) userDetails; // Asegúrate de que Usuario sea tu clase de entidad

            // Obtén el cliente usando el ID del usuario
            Optional<Cliente> clienteOptional = clienteRepository.findByUsuario(user);


            if (clienteOptional.isPresent()) {

                Cliente cliente = clienteOptional.get();
                // Asegúrate de tener un método para obtener el nombre de usuario de Cliente
                return cliente.getNombreCliente();
            } else {
                return "Cliente no encontrado";
            }
        } else {
            return "Usuario no autorizado";
        }
    }


// ...

    @GetMapping("/user")
    public ResponseEntity<?> obtenerCliente(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails instanceof Usuario) {
            Usuario user = (Usuario) userDetails;

            Optional<Cliente> clienteOptional = clienteRepository.findByUsuario(user);

            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado");
        }
    }
}

