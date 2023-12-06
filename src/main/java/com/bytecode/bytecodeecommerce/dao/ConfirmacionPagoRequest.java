package com.bytecode.bytecodeecommerce.dao;

import com.bytecode.bytecodeecommerce.models.ItemCarrito;
import lombok.Data;

import java.util.List;

@Data
public class ConfirmacionPagoRequest {
    private String paymentIntentId;
   private Long carritoId;
}