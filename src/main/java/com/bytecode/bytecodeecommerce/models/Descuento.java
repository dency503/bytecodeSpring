package com.bytecode.bytecodeecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Descuento", schema = "Productos")
public class Descuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DescuentoID")
    private int descuentoId;

    @ManyToOne
    @JoinColumn(name = "ProductoID")
    @JsonBackReference
    private Producto producto;

    @Column(name = "PorcentajeDescuento")
    private float porcentajeDescuento;

    @Column(name = "FechaInicio")
    private Date fechaInicio;

    @Column(name = "FechaFin")
    private Date fechaFin;

    @Override
    public String toString() {
        return "Descuento{" +
                "descuentoId=" + descuentoId +
                ", porcentajeDescuento=" + porcentajeDescuento +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                // No llames a producto.toString() aquí, evita la recursión infinita
                ", productoId=" + (producto != null ? producto.getProductoId() : null) +
                '}';
    }
}
