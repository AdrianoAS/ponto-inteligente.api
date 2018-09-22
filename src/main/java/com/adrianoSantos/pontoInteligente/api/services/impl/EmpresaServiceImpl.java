package com.adrianoSantos.pontoInteligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianoSantos.pontoInteligente.api.entities.Empresa;
import com.adrianoSantos.pontoInteligente.api.repository.EmpresaRepository;
import com.adrianoSantos.pontoInteligente.api.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {
	private static final Logger logger = LoggerFactory.getLogger(EmpresaServiceImpl.class);
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		logger.info("Buscando uma empresa por cnpj {}", cnpj);
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		logger.info("Persistindo uma empresa ", empresa);
		return empresaRepository.save(empresa);
	}

	
}
