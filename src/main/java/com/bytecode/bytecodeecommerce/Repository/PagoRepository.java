package com.bytecode.bytecodeecommerce.Repository;

import com.bytecode.bytecodeecommerce.models.MetodoPago;
import com.bytecode.bytecodeecommerce.models.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago,Integer> {
}
