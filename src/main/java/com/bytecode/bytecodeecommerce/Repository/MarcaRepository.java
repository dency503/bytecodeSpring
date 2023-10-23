package com.bytecode.bytecodeecommerce.Repository;

import com.bytecode.bytecodeecommerce.models.Cargo;
import com.bytecode.bytecodeecommerce.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca,Integer> {
}
