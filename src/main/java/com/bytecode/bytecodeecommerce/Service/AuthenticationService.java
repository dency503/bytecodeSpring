package com.bytecode.bytecodeecommerce.Service;


import com.bytecode.bytecodeecommerce.dao.request.SignUpRequest;
import com.bytecode.bytecodeecommerce.dao.request.SigninRequest;
import com.bytecode.bytecodeecommerce.dao.response.JwtAuthenticationResponse;


public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}

