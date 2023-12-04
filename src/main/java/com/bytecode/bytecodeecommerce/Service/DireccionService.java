package com.bytecode.bytecodeecommerce.Service;

import com.bytecode.bytecodeecommerce.models.Direccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DireccionService {

    Direccion obtenerDireccionPorId(int idDireccion);

    Page<Direccion> obtenerDirecciones(Pageable pageable);

    Direccion guardarDireccion(Direccion direccion);

    Direccion modificarDireccion(int idDireccion, Direccion nuevaDireccion);

    void eliminarDireccion(int idDireccion);
}
