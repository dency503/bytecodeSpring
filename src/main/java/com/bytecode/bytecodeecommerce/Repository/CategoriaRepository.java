package com.bytecode.bytecodeecommerce.Repository;

import com.bytecode.bytecodeecommerce.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
