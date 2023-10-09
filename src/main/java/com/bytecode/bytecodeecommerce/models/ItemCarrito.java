package com.bytecode.bytecodeecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    // getters y setters
}
