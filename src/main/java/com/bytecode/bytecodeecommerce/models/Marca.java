package com.bytecode.bytecodeecommerce.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Marca", schema = "Productos")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MarcaID")
    private int marcaId;



    @NotEmpty(message = "El nombre de la marca no puede estar vacío")
    @Column(name = "NombreMarca")
    private String nombreMarca;
}
