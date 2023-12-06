package com.bytecode.bytecodeecommerce.Service;



import com.bytecode.bytecodeecommerce.models.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface VentaService {
    Page<Venta> obtenerVentas(Pageable pageable);

    Venta obtenerVentaPorId(int ventaId);

    Venta guardarVenta(Venta venta);

    Venta modificarVenta(int ventaId, Venta nuevaVenta);

    @Transactional
    String convertirCarritoAVenta(Long carritoId);

    void eliminarVenta(int ventaId);
}
