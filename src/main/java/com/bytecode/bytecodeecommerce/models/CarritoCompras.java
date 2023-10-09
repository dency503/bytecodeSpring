package com.bytecode.bytecodeecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    private Cliente cliente;

    @OneToMany(mappedBy = "carritoCompras", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<ItemCarrito> items = new ArrayList<>();  // Inicializa la lista en el constructor


}
