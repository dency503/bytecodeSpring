package com.bytecode.bytecodeecommerce.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Cargos", schema = "Empleados")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CargoID")
    private int cargoId;


    @NotEmpty(message = "El nombre del cargo no puede estar vacío")
    @Column(name = "NombreCargo")
    private String nombreCargo;


}

