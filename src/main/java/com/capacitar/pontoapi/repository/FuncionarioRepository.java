package com.capacitar.pontoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capacitar.pontoapi.model.Funcionario;

@Transactional(readOnly = true)
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Funcionario findByCpf(String cpf);
	Funcionario findByEmail(String Email);
	Funcionario findByCpfOrEmail(String cpf, String email);
	
}
