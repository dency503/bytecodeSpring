package com.bytecode.bytecodeecommerce.Service.Impl;


import com.bytecode.bytecodeecommerce.Repository.EmpleadoRepository;
import com.bytecode.bytecodeecommerce.Service.EmpleadoService;
import com.bytecode.bytecodeecommerce.models.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> obtenerTodosEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }



    @Override
    public void guardarEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
}
