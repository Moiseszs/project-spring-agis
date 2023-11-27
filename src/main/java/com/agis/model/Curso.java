package com.agis.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="curso")
public class Curso {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	
	@Column(name="nome", length = 200)
	private String nome;
	
	@Column(name="carga_horaria")
	private int cargaHoraria;
	
	@Column(name="sigla", length=100)
	private String sigla;
	
	@Column(name="nota_enade")
	private float notaEnade;

    @Column(name = "turno", length = 10)
    private String turno;

    @OneToMany(mappedBy = "curso")
    private List<Disciplina> disciplinas;

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
	
	public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
