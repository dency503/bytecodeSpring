package com.bytecode.bytecodeecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Clientes", schema = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClienteID")
    private Long clienteId;

    @NotEmpty(message = "El nombre del cliente no puede estar vacío")
    @Column(name = "NombreCliente")
    private String nombreCliente;

    @NotEmpty(message = "El apellido del cliente no puede estar vacío")
    @Column(name = "ApellidoCliente")
    private String apellidoCliente;

    @Email(message = "Debe ingresar un correo electrónico válido")
    @NotEmpty(message = "El correo electrónico no puede estar vacío")
    @Column(name = "Email")
    private String email;
     @ManyToOne
       @JoinColumn(name = "UsuarioID")
     @JsonIgnore
     @ToString.Exclude
       private Usuario usuario;

    @Pattern(regexp = "\\d{10}", message = "El número de teléfono debe tener 10 dígitos")
    @Column(name = "Telefono")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "ID_Direccion")
    private Direccion direccion;

    @Column(name = "FechaCreacion")
    private Date fechaCreacion;


   /* @Override
    public String toString() {
        return "Cliente{" +
                "clienteId=" + clienteId +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", apellidoCliente='" + apellidoCliente + '\'' +
                ", email='" + email + '\'' +
                '}';
    }*/
}
