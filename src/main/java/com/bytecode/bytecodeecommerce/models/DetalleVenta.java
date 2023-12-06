package com.bytecode.bytecodeecommerce.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "DetalleVenta", schema = "Ventas")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DetalleVentaID")
    private int detalleVentaId;

    @ManyToOne
    @JoinColumn(name = "VentaID")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "ProductoID")
    private Producto producto;

    @Column(name = "Cantidad")
    private int cantidad;

    private double precioUnitario;
}

