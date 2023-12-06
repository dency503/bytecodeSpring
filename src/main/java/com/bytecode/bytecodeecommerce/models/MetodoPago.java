package com.bytecode.bytecodeecommerce.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Entity
@Table(name = "MetodosPago", schema = "Ventas")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MetodoPagoID")
    private int metodoPagoId;

    @NotEmpty(message = "El tipo de método de pago no puede estar vacío")
    @Column(name = "nombre")
    private String nombre;
}
