package com.bytecode.bytecodeecommerce.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Entity
@Table(name = "direcciones", schema = "direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private int idDireccion;

    @NotEmpty(message = "La línea de dirección no puede estar vacía")
    @Column(name = "linea1")
    private String linea1;

    @ManyToOne
    @JoinColumn(name = "id_distrito")
    private Distrito distrito;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    @Column(name = "pais")
    private String pais;
}
