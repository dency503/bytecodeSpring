package com.bytecode.bytecodeecommerce.controllers;



import com.bytecode.bytecodeecommerce.Service.AuthenticationService;
import com.bytecode.bytecodeecommerce.dao.request.SignUpRequest;
import com.bytecode.bytecodeecommerce.dao.request.SigninRequest;
import com.bytecode.bytecodeecommerce.dao.response.JwtAuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }
//@CrossOrigin("*")
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {

        return ResponseEntity.ok(authenticationService.signin(request));
    }
}