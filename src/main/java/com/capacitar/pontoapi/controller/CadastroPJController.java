package com.capacitar.pontoapi.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capacitar.pontoapi.dtos.CadastroPJDto;
import com.capacitar.pontoapi.enums.PerfilEnum;
import com.capacitar.pontoapi.model.Empresa;
import com.capacitar.pontoapi.model.Funcionario;
import com.capacitar.pontoapi.response.Response;
import com.capacitar.pontoapi.service.EmpresaService;
import com.capacitar.pontoapi.service.FuncionarioService;
import com.capacitar.pontoapi.utils.PasswordUtils;

@RestController
@RequestMapping("/api/cadastrar-pj")
@CrossOrigin(origins = "*")
public class CadastroPJController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroPJController.class);
	
	@Autowired
	private FuncionarioService funcService;
	
	@Autowired
	private EmpresaService empService;

	public CadastroPJController() {
		
	}
	
	/**
	 * Cadastra uma pessoa juridica no sistema.
	 * 
	 * @param cadastroPJDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroPJDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroPJDto>> cadastrar(@Valid @RequestBody CadastroPJDto cadastroPJDto,
			BindingResult result) throws NoSuchAlgorithmException{
		
		log.info("Cadastrando PJ: {}", cadastroPJDto.toString());
		Response<CadastroPJDto> response = new Response<CadastroPJDto>();
		
		validarDadosExistentes(cadastroPJDto, result);
		Empresa empresa = this.converterDtoParaEmpresa(cadastroPJDto);
		Funcionario funcionario = this.converterDtoParaFuncionario(cadastroPJDto, result);
		
		//Se tem erro
		if (result.hasErrors()) {
			
			log.error("Erro validando dados de cadastro PJ: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.empService.persistir(empresa);
		funcionario.setEmpresa(empresa);
		this.funcService.persistirFuncionario(funcionario);
		
		response.setData(this.converterCadastroPJDto(funcionario));
		return ResponseEntity.ok(response);
	}
	
	

	/**
	 *Verifica se a empresa ou funcionario já existem na base de dados afim de evitar duplicidade
	 * 
	 * @param cadastroPJDto
	 * @param result
	 */
	private void validarDadosExistentes( CadastroPJDto cadastroPJDto, BindingResult result) {
		
		this.empService.buscarPorCNPJ(cadastroPJDto.getCnpj())
				.ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente.")));

		this.funcService.buscarPorCPF(cadastroPJDto.getCpf())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

		this.funcService.buscarPorEmail(cadastroPJDto.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));
	}
	
	
	
	/**
	 * Converte os dados do DTO para empresa.
	 * 
	 * @param cadastroPJDto
	 * @return Empresa
	 */
	private Empresa converterDtoParaEmpresa(@Valid CadastroPJDto cadastroPJDto) {
		
		Empresa empresa = new Empresa();
		empresa.setCnpj(cadastroPJDto.getCnpj());
		empresa.setRazaoSocial(cadastroPJDto.getRazaoSocial());
		
		return empresa;
	}
	
	
	
	/**
	 * Converte os dados do DTO para funcionário.
	 * 
	 * @param cadastroPJDto
	 * @param result
	 * @return Funcionario
	 * @throws NoSuchAlgorithmException
	 */
	private Funcionario converterDtoParaFuncionario(@Valid CadastroPJDto cadastroPJDto, BindingResult result)
					throws NoSuchAlgorithmException{
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(cadastroPJDto.getNome());
		funcionario.setEmail(cadastroPJDto.getEmail());
		funcionario.setCpf(cadastroPJDto.getCpf());
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(PasswordUtils.gerarBCrypt(cadastroPJDto.getSenha()));
		
		return funcionario;
	}
	
	/**
	 * Popula o DTO de cadastro com os dados do funcionário e empresa.
	 * 
	 * @param funcionario
	 * @return CadastroPJDto
	 */
	private CadastroPJDto converterCadastroPJDto(Funcionario funcionario) {
		
		CadastroPJDto cadastroPJDtop = new CadastroPJDto();
		cadastroPJDtop.setId(funcionario.getId());
		cadastroPJDtop.setNome(funcionario.getNome());
		cadastroPJDtop.setEmail(funcionario.getEmail());
		cadastroPJDtop.setCpf(funcionario.getCpf());
		cadastroPJDtop.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
		cadastroPJDtop.setCnpj(funcionario.getEmpresa().getCnpj());
		
		return cadastroPJDtop;		
	}
	
	
}
