package com.bytecode.bytecodeecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "Productos", schema = "Productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductoID")
    private int productoId;

    @NotEmpty(message = "El nombre del producto no puede estar vacío")
    @Column(name = "NombreProducto")
    private String nombreProducto;
    @NotEmpty(message = "El imagenURl del producto no puede estar vacío")
    @Column(name = "ImagenURl")
    private String imagenURl;
    @Column(name = "DescripcionProducto", columnDefinition = "TEXT")
    private String descripcionProducto;

    @Column(name = "Precio")
    private double precio;

    @Column(name = "Stock")
    private int stock;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "MarcaID")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "CategoriaID")
    private Categoria categoria;

    @OneToOne(mappedBy = "producto")
    @JsonIgnore
    private Descuento descuento;
}
