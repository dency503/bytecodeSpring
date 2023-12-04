package com.bytecode.bytecodeecommerce.Service;

import com.bytecode.bytecodeecommerce.models.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmpleadoService {
    Page<Empleado> obtenerTodosEmpleados(Pageable pageable);
    Empleado obtenerEmpleadoPorId(Long id);
    void guardarEmpleado(Empleado empleado);
    void eliminarEmpleado(Long id);

    boolean actualizarEmpleado(Long id, Empleado empleado);
}
