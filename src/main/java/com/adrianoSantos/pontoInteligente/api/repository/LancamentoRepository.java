package com.adrianoSantos.pontoInteligente.api.repository;

import java.util.List;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adrianoSantos.pontoInteligente.api.entities.Funcionario;
import com.adrianoSantos.pontoInteligente.api.entities.Lancamento;

@Repository
@Transactional(readOnly=true)
@NamedNativeQueries({
	@NamedNativeQuery(name="LancamentoRepository.findByFuncionarioId",
			query="Select lancamento from Lancamento lancamento Where lancamento.funcionario.id = :funcionarioId")
})
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	List<Lancamento> findByfuncionarioId(@Param ("funcionarioId")Long funcionarioId);	
	
	Page<Lancamento> findByFuncionarioId(@Param ("funcionarioId")Long funcionarioId, Pageable pageable);

}
