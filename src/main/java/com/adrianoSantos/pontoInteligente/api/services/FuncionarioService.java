package com.adrianoSantos.pontoInteligente.api.services;

import java.util.Optional;

import com.adrianoSantos.pontoInteligente.api.entities.Funcionario;


public interface FuncionarioService {
	
	Optional<Funcionario> busucarPorCpf(String cpf);
	
	Optional<Funcionario> buscarPorEmail(String email);
	
	
	Optional<Funcionario> persistir(Funcionario funcionario);
}
