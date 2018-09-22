package com.adrianoSantos.pontoInteligente.api.test;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.adrianoSantos.pontoInteligente.api.entities.Empresa;
import com.adrianoSantos.pontoInteligente.api.entities.Funcionario;
import com.adrianoSantos.pontoInteligente.api.entities.Lancamento;
import com.adrianoSantos.pontoInteligente.api.enums.PerfilEnum;
import com.adrianoSantos.pontoInteligente.api.enums.TipoEnum;
import com.adrianoSantos.pontoInteligente.api.repository.EmpresaRepository;
import com.adrianoSantos.pontoInteligente.api.repository.FuncionarioRepository;
import com.adrianoSantos.pontoInteligente.api.repository.LancamentoRepository;
import com.adrianoSantos.pontoInteligente.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private static final String EMAIL = "email@gmail.com";
	private static final String CPF = "24291173474";
	SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	private Long funcionarioid;
	
	@Before
	public void setUp() throws Exception {
		Empresa empresa = empresaRepository.save(obterDadosEmpresa());
	
		Funcionario funcionario = funcionarioRepository.save(obterDadosFuncionatio(empresa));
		funcionarioid = funcionario.getId();
		lancamentoRepository.save(oberDadosLancamentos(funcionario));
		lancamentoRepository.save(oberDadosLancamentos(funcionario));
	}
	
	@After
	public final void tearDown() {
		empresaRepository.deleteAll();
	}
	
	@Test
	public void testBuscarLancamentoPorFuncionario() {
		List<Lancamento> lancamentos = lancamentoRepository.findByfuncionarioId(funcionarioid);
		assertEquals(2, lancamentos.size());
	}
	@Test
	public void testBuscarLancamentoPorFuncionarioIdPaginado() {
		PageRequest page = new PageRequest(0, 10);
		Page<Lancamento> lancamentos = lancamentoRepository.findByFuncionarioId(funcionarioid, page);
		assertEquals(2, lancamentos.getTotalElements());
	 
	}
	
	@SuppressWarnings("unused")
	private Lancamento oberDadosLancamentos(Funcionario funcionario) {
		Lancamento lancamento = new Lancamento();
		lancamento.setData(new Date(System.currentTimeMillis()));
		lancamento.setTipo(TipoEnum.INICIO_ALMOCO);
		lancamento.setFuncionario(funcionario);
		return lancamento;
	}
	
	
	@SuppressWarnings("unused")
	private Funcionario obterDadosFuncionatio(Empresa empresa) throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf("45633314816");
		funcionario.setEmail("Adriano@gmail.com");
		funcionario.setNome("Adriano");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBcrypt("1237"));
		funcionario.setEmpresa(empresa);
		return funcionario;
	}
	
	@SuppressWarnings("unused")
	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setCnpj("51463645000100");
		empresa.setDataAtualizacao(new Date(System.currentTimeMillis()));
		empresa.setRazaoSocial("Empresa de exemplo");
		return empresa;
	}
	
	
}
