package com.bytecode.bytecodeecommerce.Service;



import com.bytecode.bytecodeecommerce.Repository.PagoRepository;
import com.bytecode.bytecodeecommerce.models.Pago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;

    @Autowired
    public PagoServiceImpl(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public Page<Pago> obtenerTodosPagos(Pageable pageable) {
        return pagoRepository.findAll(pageable);
    }

    @Override
    public Pago obtenerPagoPorId(int id) {
        return pagoRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarPago(Pago pago) {
        pagoRepository.save(pago);
    }

    @Override
    public void eliminarPago(int id) {
        pagoRepository.deleteById(id);
    }
}
