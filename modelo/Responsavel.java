package com.provaN1.ThiagoResponsavel.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Responsavel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, message = "O nome deve ter no mínimo 3 carateres")
	private String nome;
	
	@NotBlank (message = "Insira um telefone")
	private String telefone; 
	
	@NotBlank(message = "Insira uma formação")
	private String formacao;
		
	@NotNull
	@Min(value = 300, message = "Insira um Salario Válido")
	private int salario;
	
	@NotNull
	@NotBlank(message = "Escreva uma profissão válida ")
	private String profissao;
	
	@OneToMany(mappedBy = "responsavel")
	private List<Filho> filhos;
	

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
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getFormacao() {
		return formacao;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public List<Filho> getFilhos() {
		return filhos;
	}
	public void setFilhos(List<Filho> filhos) {
		this.filhos = filhos;
	}


}

