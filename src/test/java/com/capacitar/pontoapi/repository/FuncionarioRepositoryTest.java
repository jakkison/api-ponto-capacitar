package com.capacitar.pontoapi.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.capacitar.pontoapi.enums.PerfilEnum;
import com.capacitar.pontoapi.model.Empresa;
import com.capacitar.pontoapi.model.Funcionario;
import com.capacitar.pontoapi.utils.PasswordUtils;
//import com.capacitar.pontoapi.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {

	@Autowired
	private FuncionarioRepository funcRepository;
	
	@Autowired
	private EmpresaRepository empRepository;
	
	
	private static final String EMAIL = "email@teste.com";
	private static final String CPF = "03682351060";
	
	@Before
	public void setUp() throws NoSuchAlgorithmException {
		
		Empresa empresa = this.empRepository.save(obterDadosEmpresa());
		this.funcRepository.save(obterDadosFuncionario(empresa));
	}
	
	@After
	public final void tearDown() {
		this.empRepository.deleteAll();
	}
	
	
	@Test
	public void testBuscarFuncionarioPorEmail() {
		
		Funcionario funcionario = this.funcRepository.findByEmail(EMAIL);
		assertEquals(EMAIL, funcionario.getEmail());
	}
	
	@Test
	public void testBuscarFuncionarioPorCPF() {
		
		Funcionario funcionario = this.funcRepository.findByCpf(CPF);
		assertEquals(CPF, funcionario.getCpf());
	}
	
	
	@Test
	public void testBuscarFuncionarioPorEmailOuCPF() {
		
		Funcionario funcionario = this.funcRepository.findByCpfOrEmail(CPF, EMAIL);
		assertNotNull(funcionario);
	}
	
	
	@Test
	public void testBuscarFuncionarioPorEmailOuCPFComEmailInvalido() {
		
		Funcionario funcionario = this.funcRepository.findByCpfOrEmail(CPF, "email@invalido.com");
		assertNotNull(funcionario);
	}
	
	@Test
	public void testBuscarFuncionarioPorEmailOuCPFComCPFInvalido() {
		
		Funcionario funcionario = this.funcRepository.findByCpfOrEmail("03682351054", EMAIL);
		assertNotNull(funcionario);
	}
	
	
	private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Funcionario Dos Testes Unitários");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBCrypt("123456"));
		funcionario.setCpf(CPF);
		funcionario.setEmail(EMAIL);
		funcionario.setEmpresa(empresa);
		
		return funcionario;
	}
	
	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		
		empresa.setCnpj("51463645000100");
		empresa.setRazaoSocial("Empresa de teste");
		
		return empresa;
	}
	
	
}
