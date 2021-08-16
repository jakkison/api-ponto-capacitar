package com.capacitar.pontoapi.service;

import java.util.Optional;

import com.capacitar.pontoapi.model.Empresa;

public interface EmpresaService {

	/**
	 * 
	 * Retorna uma empresa dado um CNPJ
	 * 
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscarPorCNPJ(String cnpj);
	
	/**
	 * 
	 * Cadastra uma nova Empresa no banco de dados
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);
	
}
