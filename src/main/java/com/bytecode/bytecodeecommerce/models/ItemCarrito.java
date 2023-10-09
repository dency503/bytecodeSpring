package com.bytecode.bytecodeecommerce.models;

import com.bytecode.bytecodeecommerce.models.CarritoCompras;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ItemCarrito", schema = "Productos")
public class ItemCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ItemID")
    private int itemId;

    @ManyToOne
    @JoinColumn(name = "CarritoID")

    @JsonIgnore
    @ToString.Exclude
    private CarritoCompras carritoCompras;

    @ManyToOne
    @JoinColumn(name = "ProductoID")
    private Producto producto;

    @Column(name = "Cantidad")
    private int cantidad;

    public ItemCarrito( Producto producto, int cantidad) {

        this.producto = producto;
        this.cantidad = cantidad;
    }
}
