package com.bytecode.bytecodeecommerce.Service.Impl;


import com.bytecode.bytecodeecommerce.models.Municipio;
import com.bytecode.bytecodeecommerce.models.Departamento;
import com.bytecode.bytecodeecommerce.Repository.MunicipioRepository;
import com.bytecode.bytecodeecommerce.Service.MunicipioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MunicipioServiceImpl implements MunicipioService {

    private final MunicipioRepository municipioRepository;

    public MunicipioServiceImpl(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    @Override
    public Page<Municipio> getAllMunicipios(Pageable pageable) {
        return municipioRepository.findAll(pageable);
    }

    @Override
    public Municipio getMunicipioById(String id) {
        return municipioRepository.findById(id).orElse(null);
    }

    @Override
    public Municipio saveMunicipio(Municipio municipio) {
        return municipioRepository.save(municipio);
    }

    @Override
    public Municipio updateMunicipio(String id, Municipio updatedMunicipio) {
        Municipio existingMunicipio = getMunicipioById(id);

        if (existingMunicipio != null) {
            existingMunicipio.setMunicipio(updatedMunicipio.getMunicipio());
            existingMunicipio.setDepartamento(updatedMunicipio.getDepartamento());
            return municipioRepository.save(existingMunicipio);
        }

        return null; // Municipio with the given ID not found
    }

    @Override
    public void deleteMunicipio(String id) {
        municipioRepository.deleteById(id);
    }

    @Override
    public Page<Municipio> getMunicipiosByDepartamento(Departamento departamento, Pageable pageable) {
        return municipioRepository.findByDepartamento(departamento, pageable);
    }
}
