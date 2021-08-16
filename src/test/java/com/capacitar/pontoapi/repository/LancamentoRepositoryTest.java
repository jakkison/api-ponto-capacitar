package com.capacitar.pontoapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.capacitar.pontoapi.enums.PerfilEnum;
import com.capacitar.pontoapi.enums.TipoEnum;
import com.capacitar.pontoapi.model.Empresa;
import com.capacitar.pontoapi.model.Funcionario;
import com.capacitar.pontoapi.model.Lancamento;
import com.capacitar.pontoapi.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

	@Autowired
	private LancamentoRepository lancRepository;
	
	@Autowired
	private FuncionarioRepository funcRepository;
	
	@Autowired
	private EmpresaRepository empRepository;
	
	private Long idFuncionario;
	
	
	@Before
	public void setUp() throws NoSuchAlgorithmException {
		Empresa  empresa = this.empRepository.save(obterDadosEmpresa());
		Funcionario funcionario = this.funcRepository.save(obterDadosFuncionario(empresa));
		this.idFuncionario = funcionario.getId();
		
		
		this.lancRepository.save(obterDadosLancamentos(funcionario));
		this.lancRepository.save(obterDadosLancamentos(funcionario));
	
	}
	
	@After
	public void tearDown() throws Exception{
		this.empRepository.deleteAll();
	}
	
	
	@Test
	public void testBuscarLancamentoPorFuncionarioID() {
		
		List<Lancamento> lancamentos = this.lancRepository.findByFuncionarioId(idFuncionario);
		assertEquals(2, lancamentos.size());
	}
	
	
	@Test
	public void testBuscarLancamentosPorFuncionarioIdPaginado() {
		Page<Lancamento> lancamentos = this.lancRepository.findByFuncionarioId(idFuncionario, PageRequest.of(0, 10));
		
		assertEquals(2, lancamentos.getTotalElements());
	}
	
	
	
	private Lancamento obterDadosLancamentos(Funcionario funcionario) {
		Lancamento lancameto = new Lancamento();

		lancameto.setData(new Date());
		lancameto.setTipo(TipoEnum.INICIO_ALMOCO);
		lancameto.setFuncionario(funcionario);
		
		return lancameto;
	}	
	
	private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Fulano de Tal");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBCrypt("123456"));
		funcionario.setCpf("24291173474");
		funcionario.setEmail("email@email.com");
		funcionario.setEmpresa(empresa);
		
		return funcionario;
	}
	
	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		
		empresa.setRazaoSocial("Empresa de exemplo");
		empresa.setCnpj("51463645000100");
		
		return empresa;
	}
	
	
}
