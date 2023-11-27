CREATE DATABASE DbAgis
GO
USE DbAgis
GO
CREATE TABLE aluno(
	ra VARCHAR(09) PRIMARY KEY,
	nome_completo VARCHAR(200) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	data_nascimento DATE NOT NULL,
	instituicao_segundo_grau VARCHAR(200),
	ano_formacao INT,
	ano_ingresso INT,
	semestre_ingresso INT
)
GO
CREATE TABLE curso(
	codigo INT PRIMARY KEY IDENTITY(1001,1),
	nome VARCHAR(200),
	carga_horaria INT,
    turno VARCHAR(10),
	sigla VARCHAR(100),
	nota_enade DECIMAL(4,2)
)
GO
CREATE TABLE matricula(
	codigo INT PRIMARY KEY IDENTITY(5001,1),
	aluno_ra VARCHAR(09) NOT NULL,
	curso_codigo INT,
	FOREIGN KEY(aluno_ra) REFERENCES aluno(ra),
	FOREIGN KEY(curso_codigo) REFERENCES curso(codigo)
)
GO
CREATE TABLE disciplina(
	codigo INT PRIMARY KEY IDENTITY(10001,1),
	nome VARCHAR(200),
	nome_professor VARCHAR(200),
	dia_semana VARCHAR(100),
	semestre INT,
	horas_aula INT,
	curso_codigo INT,
	hora_comeco VARCHAR(50),
	FOREIGN KEY(curso_codigo) REFERENCES curso(codigo)
)
GO
CREATE TABLE vinculo(
	id INT PRIMARY KEY IDENTITY(9001,1),
	matricula_codigo INT,
	disciplina_codigo INT,
	cursada BIT,
	FOREIGN KEY(matricula_codigo) REFERENCES matricula(codigo),
	FOREIGN KEY(disciplina_codigo) REFERENCES disciplina(codigo)
)
GO
CREATE TABLE avaliacao(
	codigo INT PRIMARY KEY IDENTITY(20001,1),
    nome VARCHAR(100),
    disciplina_codigo INT,
	peso DECIMAL(4,3),
	FOREIGN KEY(disciplina_codigo) REFERENCES disciplina(codigo)
)
GO
CREATE TABLE nota(
    codigo INT IDENTITY(7001,1) PRIMARY KEY,
    vinculo_id INT,
    avaliacao_codigo INT,
    nota DECIMAL(4,2),
    FOREIGN KEY(vinculo_id) REFERENCES vinculo(id),
    FOREIGN KEY(avaliacao_codigo) REFERENCES avaliacao(codigo)
)
GO
CREATE TABLE aula(
	codigo INT PRIMARY KEY IDENTITY(5001,1),
	data_ocorrida DATE DEFAULT(GETDATE()),
	disciplina_codigo INT,
	FOREIGN KEY(disciplina_codigo) REFERENCES disciplina(codigo)
)
GO
CREATE TABLE frequencia(
    codigo INT IDENTITY(8001,1) PRIMARY KEY,
	vinculo_id INT,
	horas_aula INT,
	aula_codigo INT,
	FOREIGN KEY(vinculo_id) REFERENCES vinculo(id),
	FOREIGN KEY(aula_codigo) REFERENCES aula(codigo)
)
GO
CREATE TRIGGER vincula_disciplinas ON matricula
AFTER INSERT 
AS
	DECLARE @codigo INT
	DECLARE disciplina_cursor CURSOR
	FOR SELECT disciplina.codigo FROM disciplina, curso
	WHERE disciplina.curso_codigo = curso.codigo
	AND disciplina.semestre = 1
	AND curso.codigo = (SELECT curso_codigo FROM inserted);
	
	OPEN disciplina_cursor
	FETCH NEXT FROM disciplina_cursor INTO @codigo
	WHILE @@FETCH_STATUS = 0
	BEGIN
		INSERT INTO vinculo(disciplina_codigo, matricula_codigo) VALUES (@codigo, (SELECT codigo FROM inserted))
		FETCH NEXT FROM disciplina_cursor INTO @codigo
	END
	CLOSE disciplina_cursor
	DEALLOCATE disciplina_cursor

