package com.bytecode.bytecodeecommerce.Service.Impl;



import com.bytecode.bytecodeecommerce.Repository.VentaRepository;
import com.bytecode.bytecodeecommerce.Service.VentaService;
import com.bytecode.bytecodeecommerce.models.Venta;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {
    private final VentaRepository ventaRepository;



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
        ventaExistente.setCarritoCompras(nuevaVenta.getCarritoCompras());
        ventaExistente.setPago(nuevaVenta.getPago());
        ventaExistente.setFechaVenta(nuevaVenta.getFechaVenta());
        ventaExistente.setTotal(nuevaVenta.getTotal());
        return ventaRepository.save(ventaExistente);
    }

    @Override
    @Transactional
    public void eliminarVenta(int ventaId) {
        ventaRepository.deleteById(ventaId);
    }
}
