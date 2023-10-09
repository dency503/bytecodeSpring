package com.bytecode.bytecodeecommerce.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Empleado", schema = "Empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmpleadoID")
    private int empleadoId;

    @NotEmpty(message = "El nombre del empleado no puede estar vacío")
    @Column(name = "Nombre")
    private String nombre;

    @NotEmpty(message = "El apellido del empleado no puede estar vacío")
    @Column(name = "Apellido")
    private String apellido;

    @NotEmpty(message = "El correo electrónico no puede estar vacío")
    @Column(name = "Email")
    private String email;

    @Column(name = "FechaContratacion")
    private Date fechaContratacion;
    @ManyToOne
    @JoinColumn(name = "UsuarioID")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "ID_Direccion")
    private Direccion direccion;
}
