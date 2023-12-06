package com.bytecode.bytecodeecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Departamentos", schema = "Direccion")
public class Departamento {
    @Id
    @Column(name = "Id_Departamento")
    private String idDepartamento;

    @NotEmpty(message = "El nombre del departamento no puede estar vacío")
    @Column(name = "Departamento")

    private String departamento;

    @Column(name = "Pais")
    private String pais;
}
