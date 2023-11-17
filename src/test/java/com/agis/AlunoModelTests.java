package com.agis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.agis.model.Aluno;

@SpringBootTest
public class AlunoModelTests {

	@Test
	void testaCpfValido() {
		Aluno aluno = new Aluno();
		assertTrue(aluno.validaCpf("65794004045"));
	}
	
	@Test
	void TestaCpfComMuitosDigitos(){
		Aluno aluno = new Aluno();
		assertFalse(aluno.validaCpf("657940040458"));
	}
	
	@Test
	void TestIdade() {
		Aluno aluno = new Aluno();
		assertTrue(aluno.validaIdade(LocalDate.of(2003, 4, 18)));
	}
}
