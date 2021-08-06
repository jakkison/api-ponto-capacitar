/*package com.capacitar.pontoapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

	private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);
	
	//construtor
	public PasswordUtils() {
		
	}*/
	
	
	/**
	* Gera um hash utilizando o BCrypt.
	*
	* @param senha
	* @return String
	
	
	public static String gerarBCrypt(String senha) {
		
		if (senha == null) {
			return senha;
		}
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		return bcrypt.encode(senha);
	}*/
	
	
	/**
	* Verifica se a senha é válida.
	*
	* @param senha
	* @param senhaEncoded
	* @return boolean
	
	
	public static boolean senhaValida(String senha, String senhaEncoded) {
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		return bcrypt.matches(senha, senhaEncoded);
		
	}
	
}*/
