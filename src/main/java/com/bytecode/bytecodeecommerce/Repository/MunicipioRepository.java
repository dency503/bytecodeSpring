package com.bytecode.bytecodeecommerce.Repository;

import com.bytecode.bytecodeecommerce.models.Departamento;
import com.bytecode.bytecodeecommerce.models.Municipio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio,String> {
    Page<Municipio> findByDepartamento(Departamento departamento, Pageable pageable);
}
