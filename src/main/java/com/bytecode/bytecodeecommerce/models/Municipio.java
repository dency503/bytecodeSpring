package com.bytecode.bytecodeecommerce.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Municipios", schema = "Direccion")
public class Municipio {
    @Id
    @Column(name = "Id_Municipio")
    private String idMunicipio;

    @NotEmpty(message = "El nombre del municipio no puede estar vacío")
    @Column(name = "Municipio")
    private String municipio;

    @ManyToOne
    @JoinColumn(name = "Id_Departamento")
    private Departamento departamento;
}
