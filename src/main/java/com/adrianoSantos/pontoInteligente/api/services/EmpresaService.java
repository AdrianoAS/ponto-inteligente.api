package com.adrianoSantos.pontoInteligente.api.services;

import java.util.Optional;

import com.adrianoSantos.pontoInteligente.api.entities.Empresa;

public interface EmpresaService {

		Optional<Empresa> buscarPorCnpj(String cnpj);
		
		Empresa persistir(Empresa empresa);
}
