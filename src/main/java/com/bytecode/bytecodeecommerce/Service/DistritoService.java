package com.bytecode.bytecodeecommerce.Service;



import com.bytecode.bytecodeecommerce.models.Distrito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface DistritoService {



    @Transactional(readOnly = true)
    Distrito obtenerDistritoPorId(String idDistrito);

    Page<Distrito> obtenerDistritos(Pageable pageable);

    Distrito guardarDistrito(Distrito distrito);




    @Transactional
    Distrito modificarDistrito(String idDistrito, Distrito nuevoDistrito);

    @Transactional
    void eliminarDistrito(String idDistrito);
}
