package com.capacitar.pontoapi.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capacitar.pontoapi.model.Empresa;
import com.capacitar.pontoapi.repository.EmpresaRepository;
import com.capacitar.pontoapi.service.EmpresaService;


@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);		
	
	@Autowired
	private EmpresaRepository empRepository;
	
	@Override
	public Optional<Empresa> buscarPorCNPJ(String cnpj) {
		
		log.info("Buscando uma empresa para o CNPJ {}", cnpj);
		return Optional.ofNullable(empRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		
		log.info("Persistindo Empresa {}", empresa);
		return this.empRepository.save(empresa);
	}

}
