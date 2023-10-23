package com.bytecode.bytecodeecommerce.Repository;

import com.bytecode.bytecodeecommerce.models.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescuentoRepository extends JpaRepository<Descuento,Integer> {
}
