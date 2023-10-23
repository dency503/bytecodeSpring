package com.bytecode.bytecodeecommerce.Service;

import com.bytecode.bytecodeecommerce.models.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CargoService {
    Page<Cargo> obtenerTodosCargos(Pageable pageable);
    Cargo obtenerCargoPorId(int id);
    void guardarCargo(Cargo cargo);
    void eliminarCargo(int id);
}
