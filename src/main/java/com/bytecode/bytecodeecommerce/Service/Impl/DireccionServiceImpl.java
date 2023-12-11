package com.bytecode.bytecodeecommerce.Service.Impl;

import com.bytecode.bytecodeecommerce.Repository.ClienteRepository;
import com.bytecode.bytecodeecommerce.Repository.DireccionRepository;
import com.bytecode.bytecodeecommerce.Service.DireccionService;
import com.bytecode.bytecodeecommerce.models.Cliente;
import com.bytecode.bytecodeecommerce.models.Direccion;
import com.bytecode.bytecodeecommerce.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DireccionServiceImpl implements DireccionService {

    private final DireccionRepository direccionRepository;
    private final ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public Direccion obtenerDireccionPorId(int idDireccion) {
        return direccionRepository.findById(idDireccion)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Direccion> obtenerDirecciones(Pageable pageable) {
        return direccionRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Direccion guardarDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    @Override
    @Transactional
    public Direccion modificarDireccion(int idDireccion, Direccion nuevaDireccion) {
        Direccion direccion = obtenerDireccionPorId(idDireccion);
        direccion.setLinea1(nuevaDireccion.getLinea1());
        direccion.setDistrito(nuevaDireccion.getDistrito());
        direccion.setCodigoPostal(nuevaDireccion.getCodigoPostal());
        direccion.setPais(nuevaDireccion.getPais());
        return direccionRepository.save(direccion);
    }

    @Override
    @Transactional
    public Direccion modificarUsuarioDireccion(Authentication authentication, Direccion nuevaDireccion) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails instanceof Usuario user) {
            Optional<Cliente> clienteOptional = clienteRepository.findByUsuario(user);

            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                // Se obtiene la dirección del cliente y se actualizan los campos
                Direccion direccion = obtenerDireccionPorId(cliente.getDireccion().getIdDireccion());
                direccion.setLinea1(nuevaDireccion.getLinea1());
                direccion.setDistrito(nuevaDireccion.getDistrito());
                direccion.setCodigoPostal(nuevaDireccion.getCodigoPostal());
                direccion.setPais(nuevaDireccion.getPais());
                return direccionRepository.save(direccion);
            }
        }
        // Devuelve null si no se encuentra el cliente o no está autenticado
        return null;
    }

    @Override
    @Transactional
    public void eliminarDireccion(int idDireccion) {
        direccionRepository.deleteById(idDireccion);
    }
}
