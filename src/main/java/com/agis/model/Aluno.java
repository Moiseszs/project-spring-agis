package com.agis.model;

import java.time.LocalDate;

import javax.naming.directory.InvalidAttributesException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	
	public String getRa() {
		return ra;
	}
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setRa(String ra) {
		this.ra = ra;
	}
	
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) throws InvalidAttributesException {
		if(validaIdade(dataNascimento)) {
			this.dataNascimento = dataNascimento;
		}
		else {
			throw new InvalidAttributesException("Idade inválida");
		}
	}
	
	public void setCpf(String cpf) throws InvalidAttributesException {
		if(validaCpf(cpf)) {
			this.cpf = cpf;
		}
		else {
			throw new InvalidAttributesException("CPF Invalido");
		}
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
	
	public boolean validaIdade(LocalDate dataNascimento) {
		if(dataNascimento.compareTo(LocalDate.now()) <= -18) {
			return true;
		}
		else {
			return false;
		}
	}
}
