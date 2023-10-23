package com.bytecode.bytecodeecommerce.Service.Impl;



import com.bytecode.bytecodeecommerce.Repository.CargoRepository;
import com.bytecode.bytecodeecommerce.Service.CargoService;
import com.bytecode.bytecodeecommerce.models.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;

    @Autowired
    public CargoServiceImpl(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cargo> obtenerTodosCargos(Pageable pageable) {
        return cargoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cargo obtenerCargoPorId(int id) {
        return cargoRepository.findById(id).orElse(null);
    }


    @Override
    @Transactional
    public void guardarCargo(Cargo cargo) {
        cargoRepository.save(cargo);
    }

    @Override
    @Transactional
    public void eliminarCargo(int id) {
        cargoRepository.deleteById(id);
    }
}
