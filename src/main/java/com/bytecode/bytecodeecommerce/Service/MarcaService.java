package com.bytecode.bytecodeecommerce.Service;



import com.bytecode.bytecodeecommerce.models.Marca;

import java.util.List;

public interface MarcaService {
    List<Marca> obtenerTodasMarcas();
    Marca obtenerMarcaPorId(int id);
    void guardarMarca(Marca marca);
    void eliminarMarca(int id);
}
