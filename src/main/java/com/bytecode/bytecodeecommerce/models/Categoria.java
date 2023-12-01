package com.bytecode.bytecodeecommerce.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Categoria", schema = "Productos")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoriaID")
    private int categoriaId;



    @NotEmpty(message = "El nombre de la categoría no puede estar vacío")
    @Column(name = "NombreCategoria")
    private String nombreCategoria;
    @NotEmpty(message = "La url de la categoría no puede estar vacío")
    private String url_imagen;
}
