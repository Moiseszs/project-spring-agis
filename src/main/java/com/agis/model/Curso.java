package com.agis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="curso")
public class Curso {

	@Id
	private int codigo;
	
	@Column(name="nome", length = 200)
	private String nome;
	
	@Column(name="carga_horaria")
	private int cargaHoraria;
	
	@Column(name="sigla", length=100)
	private String sigla;
	
	@Column(name="nota_enade")
	private float notaEnade;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public float getNotaEnade() {
		return notaEnade;
	}

	public void setNotaEnade(float notaEnade) {
		this.notaEnade = notaEnade;
	}
	
	
}
