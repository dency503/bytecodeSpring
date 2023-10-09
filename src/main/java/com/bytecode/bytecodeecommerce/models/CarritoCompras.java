package com.bytecode.bytecodeecommerce.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CarritoCompras", schema = "Productos")
public class CarritoCompras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarritoID")
    private int carritoId;

    @ManyToOne
    @JoinColumn(name = "ClienteID")
    @ToString.Exclude
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ProductoID")
    private Producto producto;

    @Column(name = "Cantidad")
    private int cantidad;
}
