package com.adrianoSantos.pontoInteligente.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.adrianoSantos.pontoInteligente.api.enums.PerfilEnum;

@Entity
@Table(name="funcionario")
public class Funcionario implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome", nullable=true)
	private String nome;
	
	@Column(name="email", nullable=true)
	private String email;
	
	@Column(name="senha", nullable=true)
	private String senha;
	
	@Column(name="cpf", nullable=true)
	private String cpf;
	
	@Column(name="valor_hora", nullable=true)
	private Double valorHora;
	
	@Column(name="qtde_hora_trabalhada", nullable=true)
	private Float quatidadeHorasTrabalhada;
	
	@Column(name="qtde_hora_almoco", nullable=true)
	private Float quantidadeHorasAlmoco;
	
	@Column(name="perfil", nullable=true)
	private PerfilEnum perfil;
	
	@Column(name="data_criacao", nullable=true)
	private Date dataCriacao;
	
	@Column(name="data_atualizacao", nullable=true)
	private Date dataAtualizacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Empresa empresa;
	
	@OneToMany(mappedBy ="funcionario" , fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private  List<Lancamento> lancamentos;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Double getValorHora() {
		return valorHora;
	}
	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}
	public Float getQuatidadeHorasTrabalhada() {
		return quatidadeHorasTrabalhada;
	}
	public void setQuatidadeHorasTrabalhada(Float quatidadeHorasTrabalhada) {
		this.quatidadeHorasTrabalhada = quatidadeHorasTrabalhada;
	}
	public Float getQuantidadeHorasAlmoco() {
		return quantidadeHorasAlmoco;
	}
	public void setQuantidadeHorasAlmoco(Float quantidadeHorasAlmoco) {
		this.quantidadeHorasAlmoco = quantidadeHorasAlmoco;
	}
	@Enumerated(EnumType.STRING)
	public PerfilEnum getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	
	@Transient
	public Optional<Double> getValorHoraOpt(){
		return Optional.ofNullable(valorHora);
	}
	
	@Transient
	public Optional<Float> getQuatidadeHorasTrabalhadaOpt(){
		return Optional.ofNullable(quatidadeHorasTrabalhada);
	}
	
	@Transient
	public Optional<Float> getQuantidadeHorasAlmocoOpt(){
		return Optional.ofNullable(quantidadeHorasAlmoco);
	}
	
	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	@PrePersist
	public void prePersiste() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id +" , nome =" + nome+", emial= " + email+ ", senha= " + senha + ", cpf=" + cpf + ", valorHora=" + valorHora + ", quantidadeHorasTrabalhada=" + quatidadeHorasTrabalhada + ""
				+ ", quantidadeHorasAlmoco" + quantidadeHorasAlmoco + ", perfil=" + perfil + ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", empresa=" + empresa + " ]";
	}
}
