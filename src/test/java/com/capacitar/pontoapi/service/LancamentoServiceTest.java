package com.capacitar.pontoapi.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.capacitar.pontoapi.model.Lancamento;
import com.capacitar.pontoapi.repository.LancamentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {

	@MockBean
	private LancamentoRepository lancRepository;

	@Autowired
	private LancamentoService lancService;

	@Before
	public void setUp() throws Exception{

		BDDMockito
				.given(this.lancRepository.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
		BDDMockito.given(this.lancRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Lancamento()));
		BDDMockito.given(this.lancRepository.save(Mockito.any(Lancamento.class))).willReturn(new Lancamento());

	}

	@Test
	public void testBuscarLancamentoPorFuncionarioId() {
		
		Page<Lancamento> lancamento = this.lancService.buscarPorFuncionarioId(1L, PageRequest.of(0, 10));
		assertNotNull(lancamento);
	}
	
	@Test
	public void testBuscarLancamentoPorId() {
		
		Optional<Lancamento> lancamento = this.lancService.buscarPorId(1L);
		assertTrue(lancamento.isPresent());
	}
	
	@Test
	public void testPersistirLancamento() {
		
		Lancamento lancamento = this.lancService.persistir(new Lancamento());
		assertNotNull(lancamento);
	}
	
	
}
