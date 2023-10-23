package com.bytecode.bytecodeecommerce.Service;

import com.bytecode.bytecodeecommerce.models.Empleado;

import java.util.List;

public interface EmpleadoService {
    List<Empleado> obtenerTodosEmpleados();
    Empleado obtenerEmpleadoPorId(Long id);
    void guardarEmpleado(Empleado empleado);
    void eliminarEmpleado(Long id);
}
