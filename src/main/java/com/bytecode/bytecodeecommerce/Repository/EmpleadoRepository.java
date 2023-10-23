package com.bytecode.bytecodeecommerce.Repository;

import com.bytecode.bytecodeecommerce.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
}
