package com.adrianoSantos.pontoInteligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianoSantos.pontoInteligente.api.entities.Funcionario;
import com.adrianoSantos.pontoInteligente.api.repository.FuncionarioRepository;
import com.adrianoSantos.pontoInteligente.api.services.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	
	private static final Logger log =LoggerFactory.getLogger(FuncionarioServiceImpl.class);
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	

	@Override
	public Optional<Funcionario> busucarPorCpf(String cpf) {
		log.info("Buscando Funcionario por cpf", cpf);
		return Optional.ofNullable(funcionarioRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> buscarPorEmail(String email) {
		log.info("Buscando Funcionario por email", email);
		return Optional.ofNullable(funcionarioRepository.findByEmail(email));
	}

	
	public Optional<Optional<Funcionario>> buscarPorid(Long id) {
		log.info("Buscando Funcionario por id", id);
		return Optional.ofNullable(funcionarioRepository.findById(id));
	}

	@Override
	public Optional<Funcionario> persistir(Funcionario funcionario) {
		log.info("Buscando Funcionario por funcionario", funcionario);
		return Optional.ofNullable(funcionarioRepository.save(funcionario));
	}

}
