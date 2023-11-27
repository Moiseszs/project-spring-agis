package com.agis.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int codigo;

    @Column(name="nome", length=200, nullable = false)
    private String nome;
    
    @Column(name="nome_professor", length=200, nullable = false)
    private String nomeProfessor;

    @Column(name="dia_semana", nullable = false)
    private String diaSemana;

    @Column(name="semestre")
    private int semestre;

    @Column(name="horas_aula")
    private int horasAula;

    @ManyToOne
    @JoinColumn(name="curso_codigo")
    private Curso curso;

    @Column(name="hora_comeco")
    private String horaComeco;

    @OneToMany(mappedBy = "disciplina")
    private List<Vinculo> vinculos;

    @OneToMany(mappedBy = "disciplina")
    private List<Avaliacao> avaliacaos;

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setHoraComeco(String horaComeco) {
        this.horaComeco = horaComeco;
    }

    public void setHorasAula(int horasAula) {
        this.horasAula = horasAula;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public Curso getCurso() {
        return curso;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public String getHoraComeco() {
        return horaComeco;
    }
    
    public int getHorasAula() {
        return horasAula;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setVinculos(List<Vinculo> vinculos) {
        this.vinculos = vinculos;
    }

    public void setAvaliacaos(List<Avaliacao> avaliacaos) {
        this.avaliacaos = avaliacaos;
    }

    public List<Vinculo> getVinculos() {
        return vinculos;
    }

    public List<Avaliacao> getAvaliacaos() {
        return avaliacaos;
    }
}
