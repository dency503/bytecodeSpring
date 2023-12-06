package com.bytecode.bytecodeecommerce.Service.Impl;



import com.bytecode.bytecodeecommerce.Repository.CarritoRepository;
import com.bytecode.bytecodeecommerce.Repository.DetalleVentaRepository;
import com.bytecode.bytecodeecommerce.Repository.MetodoPagoRepository;
import com.bytecode.bytecodeecommerce.Repository.VentaRepository;
import com.bytecode.bytecodeecommerce.Service.VentaService;
import com.bytecode.bytecodeecommerce.dao.EstadoPago;
import com.bytecode.bytecodeecommerce.models.*;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {
    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
private  final CarritoRepository carritoRepository;
private final MetodoPagoRepository metodoPagoRepository;


    @Override
    @Transactional(readOnly = true)
    public Page<Venta> obtenerVentas(Pageable pageable) {
        return ventaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Venta obtenerVentaPorId(int ventaId) {
        return ventaRepository.findById(ventaId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + ventaId));
    }

    @Override
    @Transactional
    public Venta guardarVenta(Venta venta) {
        // Realiza cualquier lógica de negocio necesaria antes de guardar
        return ventaRepository.save(venta);
    }

    @Override
    @Transactional
    public Venta modificarVenta(int ventaId, Venta nuevaVenta) {
        Venta ventaExistente = obtenerVentaPorId(ventaId);
        // Copia los campos relevantes de nuevaVenta a ventaExistente

        ventaExistente.setEstadoPago(nuevaVenta.getEstadoPago());
        ventaExistente.setMetodoPago(nuevaVenta.getMetodoPago());
        ventaExistente.setFechaVenta(nuevaVenta.getFechaVenta());
        ventaExistente.setTotal(nuevaVenta.getTotal());
        return ventaRepository.save(ventaExistente);
    }
    @Transactional
    @Override
    public String convertirCarritoAVenta(Long carritoId) {
        Optional<CarritoCompras> carritoOptional = carritoRepository.findById(carritoId);
        Optional<MetodoPago> metodoPagoOptional = metodoPagoRepository.findById(1);
        if (carritoOptional.isPresent()) {
            CarritoCompras carrito = carritoOptional.get();

            // Verificar que el carrito tenga elementos
            if (carrito.getItems().isEmpty()) {
                return "El carrito está vacío. No se puede convertir a venta.";
            }

            // Crear una nueva venta
            Venta venta = new Venta();
            venta.setCliente(carrito.getCliente()); // Asigna el cliente de la venta
            venta.setFechaVenta(LocalDateTime.now());
            venta.setEstadoPago(EstadoPago.PAGADO);
venta.setMetodoPago(metodoPagoOptional.orElse(null));
           BigDecimal totalVenta = BigDecimal.ZERO; // Inicializa el total de la venta

            // Mueve los elementos del carrito a la venta
            for (ItemCarrito itemCarrito : carrito.getItems()) {
                DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setProducto(itemCarrito.getProducto());
                detalleVenta.setCantidad(itemCarrito.getCantidad());
                detalleVenta.setVenta(venta);
detalleVenta.setPrecioUnitario(itemCarrito.getProducto().getPrecio());

                // Calcula el subtotal del detalle y suma al total de la venta
                BigDecimal precioProducto = BigDecimal.valueOf(itemCarrito.getProducto().getPrecio());
                BigDecimal cantidad = BigDecimal.valueOf(itemCarrito.getCantidad());
                BigDecimal subtotal = precioProducto.multiply(cantidad);
                totalVenta = totalVenta.add(subtotal);

                detalleVentaRepository.save(detalleVenta);
                venta.getDetallesVenta().add(detalleVenta);
            }

            // Asigna el total de la venta
            venta.setTotal(totalVenta);

            ventaRepository.save(venta);
            carritoRepository.delete(carrito);

            return "SUCCESS";
        } else {
            return "Carrito no encontrado.";
        }
    }

    @Override
    @Transactional
    public void eliminarVenta(int ventaId) {
        ventaRepository.deleteById(ventaId);
    }
}
