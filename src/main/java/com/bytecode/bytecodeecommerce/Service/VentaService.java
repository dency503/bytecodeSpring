package com.bytecode.bytecodeecommerce.Service;



import com.bytecode.bytecodeecommerce.models.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VentaService {
    Page<Venta> obtenerVentas(Pageable pageable);

    Venta obtenerVentaPorId(int ventaId);

    Venta guardarVenta(Venta venta);

    Venta modificarVenta(int ventaId, Venta nuevaVenta);

    void eliminarVenta(int ventaId);
}
