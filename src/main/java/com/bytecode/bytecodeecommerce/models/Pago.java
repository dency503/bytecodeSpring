package com.bytecode.bytecodeecommerce.models;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Pago", schema = "Ventas")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PagoID")
    private int pagoId;

    @ManyToOne
    @JoinColumn(name = "MetodoPagoID")
    private MetodoPago metodoPago;

    @Column(name = "Cantidad")
    private float cantidad;
}
