package com.bytecode.bytecodeecommerce.Service.Impl;


import com.bytecode.bytecodeecommerce.Repository.EmpleadoRepository;
import com.bytecode.bytecodeecommerce.Service.EmpleadoService;
import com.bytecode.bytecodeecommerce.models.Empleado;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
 @RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;




    @Override
    @Transactional(readOnly = true)
    public Page<Empleado> obtenerTodosEmpleados(Pageable pageable) {
        return empleadoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardarEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    @Transactional
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public boolean actualizarEmpleado(Long id, Empleado empleado) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(id);

        if (empleadoOptional.isPresent()) {
            Empleado existingEmpleado = empleadoOptional.get();
            // Update the fields you want to update
            existingEmpleado.setNombre(empleado.getNombre());
            existingEmpleado.setApellido(empleado.getApellido());
            existingEmpleado.setEmail(empleado.getEmail());
            existingEmpleado.setFechaContratacion(empleado.getFechaContratacion());
            existingEmpleado.setUsuario(empleado.getUsuario());
            existingEmpleado.setDireccion(empleado.getDireccion());

            empleadoRepository.save(existingEmpleado);
            return true;
        } else {
            return false;
        }
    }
}
