package com.agis.model;

import java.time.LocalDate;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
public class Aluno {

	@Id
	@Column(name="ra", length=20)
	private String ra;
	
	@Column(name="nome_completo", length=300, nullable=false)
	private String nomeCompleto;
	
	@Column(name="cpf", length = 11, nullable=false)
	private String cpf;
	
	@Column(name="data_nascimento", nullable = false)
	private LocalDate dataNascimento;
	
    @Column(name="instituicao_segundo_grau")
    private String instituicaoSegundoGrau;

    @Column(name="ano_formacao")
    private int anoFormacao;

    @Column(name="ano_ingresso")
    private int anoIngresso;

    @Column(name="semestre_ingresso")
    private int semestreIngresso;

    @OneToOne(mappedBy = "aluno")    
    private Matricula matricula;

	public String getRa() {
		return ra;
	}
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public void setRa(String ra) {
		this.ra = ra;
	}
	
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
	
	public int somaDigitos(String cpf, int quantidade) {
		int soma = 0;
		for(int i = 0, j = quantidade + 1 ; i <= quantidade && j >= 2; j--, i++) {
			soma = ((Character.getNumericValue(cpf.charAt(i)) * j) + soma);
		}
		return soma;
	}
	
	public boolean validaCpf(String cpf) {
		if(cpf.length() <= 0 | cpf.length() > 11) {
			return false;
		}
		else {
			int primeiroDigitoVerificador = Character.getNumericValue(cpf.charAt(9));
			int segundoDigitoVerificador = Character.getNumericValue(cpf.charAt(10));
			int primeiraSoma = somaDigitos(cpf, 9);
			int segundaSoma = somaDigitos(cpf, 10);
			int primeiroRestante = (primeiraSoma * 10) % 11;
			int segundoRestante = (segundaSoma * 10) % 11;
			System.out.println(primeiroRestante);
			System.out.println(segundoRestante);
			if(primeiroRestante == primeiroDigitoVerificador) {
				if(segundoRestante == segundoDigitoVerificador) {
					return true;
				}
			}
		}
		return false;
	}

    public void setAnoIngresso() {
        int anoIngresso = LocalDate.now().getYear();
        this.anoIngresso = anoIngresso;
    }

    public void setSemestreIngresso() {
        int semestreIngresso = 0;
        if(LocalDate.now().getMonthValue() >= 6){
            semestreIngresso = 2;
        }
        else{
            semestreIngresso = 1;
        }
        this.semestreIngresso = semestreIngresso;
    }

    public int getAnoIngresso() {
        return anoIngresso;
    }

    public int getSemestreIngresso() {
        return semestreIngresso;
    }

    public void setInstituicaoSegundoGrau(String instituicaoSegundoGrau) {
        this.instituicaoSegundoGrau = instituicaoSegundoGrau;
    }

    public void setAnoFormacao(int anoFormacao) {
        this.anoFormacao = anoFormacao;
    }

    public int getAnoFormacao() {
        return this.anoFormacao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getInstituicaoSegundoGrau() {
        return instituicaoSegundoGrau;
    }
}
