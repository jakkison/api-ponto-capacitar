package com.capacitar.pontoapi.service;

import java.util.Optional;

import com.capacitar.pontoapi.model.Funcionario;

public interface FuncionarioService {

	/**
	 * 
	 * Persiste um funcionario no banco de dados;
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	Funcionario persistirFuncionario(Funcionario funcionario);
	
	/**
	 * 
	 * Busca e retorna um funcionario dado o seu CPF
	 * 
	 * @param cpf
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorCPF(String cpf);
	
	/**
	 * 
	 * Busca e retorna um funcionario dado um email.
	 * 
	 * @param email
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorEmail(String email);
	
	/**
	 * 
	 * Busca e retorna um funcionario dado seu ID.
	 * 
	 * @param id
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorId(Long id);
	
}
