package com.adrianoSantos.pontoInteligente.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.adrianoSantos.pontoInteligente.api.entities.Lancamento;

public interface LancamentoService {
	
	Page<Lancamento> buscarPorFuncionarioId(Long id, PageRequest page);
	
	
	Lancamento persistir(Lancamento lancamento);
	
	void remover(Long id);
}
