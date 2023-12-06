package com.bytecode.bytecodeecommerce.Service;

import com.bytecode.bytecodeecommerce.Repository.DepartamentoRepository;
import com.bytecode.bytecodeecommerce.models.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Transactional(readOnly = true)
    public Page<Departamento> getAllDepartamentos(Pageable pageable) {
        return departamentoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Departamento> getDepartamentoById(String idDepartamento) {
        return departamentoRepository.findById(idDepartamento);
    }
    @Transactional
    public Departamento saveDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }
    @Transactional
    public void deleteDepartamento(String idDepartamento) {
        departamentoRepository.deleteById(idDepartamento);
    }
}
