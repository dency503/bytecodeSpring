package com.bytecode.bytecodeecommerce.dao.request;


import com.bytecode.bytecodeecommerce.models.Direccion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {




    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "Nombre Cliente cannot be blank")
    private String nombreCliente;

    @NotBlank(message = "Apellido Cliente cannot be blank")
    private String apellidoCliente;

    @NotBlank(message = "Telefono cannot be blank")

    private String telefono;

    private Direccion direccion;
}