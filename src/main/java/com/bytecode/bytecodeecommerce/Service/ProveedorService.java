package com.bytecode.bytecodeecommerce.Service;

import com.bytecode.bytecodeecommerce.models.Proveedor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface ProveedorService {
    ResponseEntity<Page<Proveedor>> obtenerTodosProveedores(Pageable pageable);
    ResponseEntity<Proveedor> obtenerProveedorPorId(Long id);
    ResponseEntity<Void> guardarProveedor(Proveedor proveedor);
    ResponseEntity<Void> eliminarProveedor(Long id);

    @Transactional
    ResponseEntity<Void> modificarProveedor(Long id, Proveedor proveedor);
}
