package com.capacitar.pontoapi.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capacitar.pontoapi.model.Funcionario;
import com.capacitar.pontoapi.repository.FuncionarioRepository;
import com.capacitar.pontoapi.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);
	
	@Autowired
	private FuncionarioRepository funcRepository;
	
	@Override
	public Funcionario persistirFuncionario(Funcionario funcionario) {
		log.info("Persistindo Funcionário: {}", funcionario);
		return this.funcRepository.save(funcionario);
	}

	@Override
	public Optional<Funcionario> buscarPorCPF(String cpf) {
		log.info("Buscando Funcionário pelo CPF {} ", cpf );
		return Optional.ofNullable(this.funcRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> buscarPorEmail(String email) {
		log.info("Buscando Funcionário pelo E-mail {} ", email);
		return Optional.ofNullable(this.funcRepository.findByEmail(email));
	}

	@Override
	public Optional<Funcionario> buscarPorId(Long id) {
		log.info("Buscando Funcionário pelo ID {} ", id);
		return this.funcRepository.findById(id);
	}
	
	
	
}
