package com.capacitar.pontoapi.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.capacitar.pontoapi.model.Lancamento;
import com.capacitar.pontoapi.repository.LancamentoRepository;
import com.capacitar.pontoapi.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService{
	
	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired
	private LancamentoRepository lancRepository;

	
	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
		
		log.info("Buscando lançamento para o funcionário de ID {}", funcionarioId);
		return this.lancRepository.findByFuncionarioId(funcionarioId, pageRequest);
	}

	@Cacheable("lancamentoPorId")
	public Optional<Lancamento> buscarPorId(Long id) {
		
		log.info("Buscando lançamento pelo ID {}", id);
		return this.lancRepository.findById(id);
	}

	@CachePut("lancamentoPorId")
	public Lancamento persistir(Lancamento lancamento) {
		
		log.info("Persistindo o lançamento {}", lancamento);
		return this.lancRepository.save(lancamento);
	}

	public void remover(Long id) {
		
		log.info("Removendo o lançamento ID {}", id);
		this.lancRepository.deleteById(id);
	}

}
