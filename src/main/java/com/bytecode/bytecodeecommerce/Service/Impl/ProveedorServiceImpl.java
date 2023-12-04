package com.bytecode.bytecodeecommerce.Service.Impl;

import com.bytecode.bytecodeecommerce.Repository.ProveedorRepository;
import com.bytecode.bytecodeecommerce.Service.ProveedorService;
import com.bytecode.bytecodeecommerce.models.Proveedor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<Proveedor>> obtenerTodosProveedores(Pageable pageable) {
        Page<Proveedor> proveedores = proveedorRepository.findAll(pageable);
        return ResponseEntity.ok(proveedores);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Proveedor> obtenerProveedorPorId(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        return proveedor != null ? ResponseEntity.ok(proveedor) : ResponseEntity.notFound().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> guardarProveedor(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> eliminarProveedor(Long id) {
        proveedorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> modificarProveedor(Long id, Proveedor proveedor) {
        Proveedor existingProveedor = proveedorRepository.findById(id).orElse(null);

        if (existingProveedor != null) {
            // Assuming Proveedor has additional fields to update, update them accordingly
            existingProveedor.setNombre(proveedor.getNombre());
            existingProveedor.setEmail(proveedor.getEmail());
            existingProveedor.setTelefono(proveedor.getTelefono());
            existingProveedor.setDireccion(proveedor.getDireccion());

            proveedorRepository.save(existingProveedor);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
