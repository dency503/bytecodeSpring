package com.bytecode.bytecodeecommerce.controllers;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/resource")
@RequiredArgsConstructor
public class AuthorizationController {



        @GetMapping("/role")

        public ResponseEntity<String> sayHello(Authentication authentication) {
            // Obtén los roles del usuario autenticado
            StringBuilder roles = new StringBuilder();
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                roles.append(authority.getAuthority()).append(", ");
            }

            // Imprime los roles del usuario
            System.out.println("Roles del usuario autenticado: " + roles.toString());

            // Resto de la lógica del controlador
            return ResponseEntity.ok("your role is " +roles.toString());
        }




    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<String> sayHelloAdmin() {
        return ResponseEntity.ok("Hello Admin");
    }
}