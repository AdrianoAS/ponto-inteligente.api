package com.adrianoSantos.pontoInteligente.api.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.adrianoSantos.pontoInteligente.api.entities.Empresa;
import com.adrianoSantos.pontoInteligente.api.entities.Funcionario;
import com.adrianoSantos.pontoInteligente.api.enums.PerfilEnum;
import com.adrianoSantos.pontoInteligente.api.repository.EmpresaRepository;
import com.adrianoSantos.pontoInteligente.api.repository.FuncionarioRepository;
import com.adrianoSantos.pontoInteligente.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncinarioRepositoryTest {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private static final String EMAIL = "email@gmail.com";
	private static final String CPF = "24291173474";
	SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	@Before
	public void setUp() throws Exception{
		Empresa empresa = new Empresa();
		empresa.setCnpj("51463645000100");
		empresa.setDataAtualizacao(new Date(System.currentTimeMillis()));
		empresa.setDataCriacao(fd.parse("21/09/2018 19:01"));
		empresa.setRazaoSocial("Empresa de exemplo");
		
		Funcionario fun = new Funcionario();
		fun.setNome("Adriano Santos");
		fun.setCpf(CPF);
		fun.setDataAtualizacao(new Date(System.currentTimeMillis()));
		fun.setDataCriacao(fd.parse("20/09/2018 20:00"));
		fun.setEmail(EMAIL);
		fun.setEmpresa(empresa);
		fun.setPerfil(PerfilEnum.ROLE_USUARIO);
		fun.setSenha(PasswordUtils.gerarBcrypt("1237"));
		fun.setQuatidadeHorasTrabalhada(240.0F);
		fun.setQuantidadeHorasAlmoco(1.00F);
		List<Funcionario> func = new ArrayList<>();
		func.add(fun);
		empresa.setFuncionarios(func);
		
		empresaRepository.save(empresa);
		funcionarioRepository.saveAll(func);
	}
	
	@After
	public final void tearDown() {
		funcionarioRepository.deleteAll();
	}
	
	@Test
	public void testBuscarFuncionarioPorEmailOrCpf() {
		Funcionario fun = funcionarioRepository.findByCpfOrEmail("24291173474", EMAIL);
		
		assertNotNull(fun);
	}
	@Test
	public void testBuscarFuncionarioPorEmail() {
		Funcionario fun = funcionarioRepository.findByEmail(EMAIL);
		
		assertEquals(EMAIL,fun.getEmail());
	}
	@Test
	public void testBuscarFuncionarioPorCpf() {
		Funcionario fun = funcionarioRepository.findByCpf("24291173474");
		
		assertEquals(CPF, fun.getCpf());
	}
}
