package com.bytecode.bytecodeecommerce.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Direcciones", schema = "Direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Direccion")
    private int idDireccion;

    @NotEmpty(message = "La línea de dirección no puede estar vacía")
    @Column(name = "Linea1")
    private String linea1;

    @ManyToOne
    @JoinColumn(name = "ID_Distrito")
    private Distrito distrito;

    @Column(name = "CodigoPostal")
    private int codigoPostal;

    @Column(name = "Pais")
    private String pais;
}
