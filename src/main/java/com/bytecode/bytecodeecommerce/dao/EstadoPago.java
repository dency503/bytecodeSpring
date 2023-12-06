package com.bytecode.bytecodeecommerce.dao;



public enum EstadoPago {
    PENDIENTE("Pendiente de pago"),
    PAGADO("Pago completado"),
    RECHAZADO("Pago rechazado");

    private final String descripcion;

    EstadoPago(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
