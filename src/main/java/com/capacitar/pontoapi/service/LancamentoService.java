package com.capacitar.pontoapi.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.capacitar.pontoapi.model.Lancamento;

public interface LancamentoService {

	
	/**
	 * Retorna uma lista de lançamentos de um determinado funcionário
	 * 
	 * @param funcionarioId
	 * @param pageRequest
	 * @return Page(Lancamento)
	 */
	Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest);
	
	
	/**
	 * Retorna um lançamento por ID
	 * 
	 * @param id
	 * @return Optional<Lancamento>
	 */
	Optional<Lancamento> buscarPorId(Long id);
	
	
	/**
	 * Persiste um lançamento na base de dados.
	 * 
	 * @param lancamento
	 * @return Lancamento
	 */
	Lancamento persistir(Lancamento lancamentos);
	
	/**
	 * Remove um lançamento da base de dados
	 * 
	 * @param id
	 */
	void remover(Long id);
	
}
