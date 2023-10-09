package com.bytecode.bytecodeecommerce.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Venta", schema = "Ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VentaID")
    private int ventaId;

    @ManyToOne
    @JoinColumn(name = "CarritoID")
    private CarritoCompras carritoCompras;

    @ManyToOne
    @JoinColumn(name = "PagoID")
    private Pago pago;

    @Column(name = "FechaVenta")
    private Date fechaVenta;

    @Column(name = "Total")
    private BigDecimal total;
}
