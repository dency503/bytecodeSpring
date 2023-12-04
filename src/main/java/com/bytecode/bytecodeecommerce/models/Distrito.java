package com.bytecode.bytecodeecommerce.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Distritos", schema = "Direccion")
public class Distrito {
    @Id
    @Column(name = "Id_Distrito")
    private String idDistrito;

    @NotEmpty(message = "El nombre del distrito no puede estar vacío")
    @Column(name = "Distrito")
    private String distrito;

    @ManyToOne
    @JoinColumn(name = "Id_Municipio")
    @ToString.Exclude
    private Municipio municipio;

    // Agrega este constructor
    public Distrito(String idDistrito) {
        this.idDistrito = idDistrito;
    }
}
