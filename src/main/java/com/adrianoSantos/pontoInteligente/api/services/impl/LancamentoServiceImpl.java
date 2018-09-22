package com.adrianoSantos.pontoInteligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.adrianoSantos.pontoInteligente.api.entities.Lancamento;
import com.adrianoSantos.pontoInteligente.api.repository.LancamentoRepository;
import com.adrianoSantos.pontoInteligente.api.services.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {
	
	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	

	@Override
	public Page<Lancamento> buscarPorFuncionarioId(Long id, PageRequest page) {
		log.info("Buscando lançamento para  o funcionario ID",id);
		return lancamentoRepository.findByFuncionarioId(id, page);
	}

	
	public Optional<Optional<Lancamento>> buscarPorId(Long id) {
		log.info("Buscando lancamento por funcionario id", id);
		return Optional.ofNullable(lancamentoRepository.findById(id));
	}

	@Override
	public Lancamento persistir(Lancamento lancamento) {
		log.info("Persistindo lancamento ",lancamento);
		return lancamentoRepository.save(lancamento);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo lançamento", id);
		lancamentoRepository.deleteById(id);
	}

}
