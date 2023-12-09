package com.bytecode.bytecodeecommerce.models;

import com.bytecode.bytecodeecommerce.dao.EstadoPago;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data

@Entity
@Table(name = "Venta", schema = "Ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VentaID")
    private int ventaId;
    public Venta(/* otros parámetros */) {
        this.detallesVenta = new ArrayList<>();
        // Inicializa otras propiedades si es necesario
    }
    @ManyToOne
    @JoinColumn(name = "idMetodoPago")
    private MetodoPago metodoPago;

    @Column(name = "FechaVenta")
    private LocalDateTime fechaVenta;
    @Column(name = "Total")
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "EstadoPago")
    private EstadoPago estadoPago;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detallesVenta;
}
