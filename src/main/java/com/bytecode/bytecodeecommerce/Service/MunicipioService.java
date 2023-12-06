package com.bytecode.bytecodeecommerce.Service;



import com.bytecode.bytecodeecommerce.models.Municipio;
import com.bytecode.bytecodeecommerce.models.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MunicipioService {

    // Retrieve all municipalities with pagination
    Page<Municipio> getAllMunicipios(Pageable pageable);

    // Retrieve a municipality by ID
    Municipio getMunicipioById(String id);

    // Save a new municipality
    Municipio saveMunicipio(Municipio municipio);

    // Update an existing municipality
    Municipio updateMunicipio(String id, Municipio updatedMunicipio);

    // Delete a municipality by ID
    void deleteMunicipio(String id);

    // Find municipalities by department
    Page<Municipio> getMunicipiosByDepartamento(Departamento departamento, Pageable pageable);
}
