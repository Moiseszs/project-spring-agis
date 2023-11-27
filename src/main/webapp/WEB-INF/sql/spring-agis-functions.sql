USE DbAgis

CREATE FUNCTION lista_disciplinas_disponiveis(@ra VARCHAR(09))
RETURNS TABLE
AS
RETURN
(
SELECT disciplina.* FROM matricula, curso, disciplina WHERE disciplina.curso_codigo = curso.codigo AND matricula.aluno_ra = @ra AND disciplina.codigo NOT IN (
SELECT disciplina.codigo FROM matricula, curso, disciplina, vinculo
WHERE matricula.codigo = vinculo.matricula_codigo
AND disciplina.codigo = vinculo.disciplina_codigo
AND matricula.aluno_ra = @ra)
)



DECLARE @faltas_totais INT
SET @faltas_totais = (SELECT COUNT(frequencia.horas_aula) FROM aluno, frequencia, vinculo, matricula WHERE aluno.ra = matricula.aluno_ra 
AND vinculo.matricula_codigo = matricula.codigo
AND frequencia.vinculo_id = vinculo.id
GROUP BY aluno.nome_completo)

SELECT CONCAT(aluno.ra, disciplina.codigo) AS id, aluno.nome_completo, disciplina.nome, COUNT(frequencia.horas_aula) - disciplina.horas_aula, CAST(COUNT(frequencia.horas_aula) AS DECIMAL(4,2)) / (COUNT(aula.codigo) * disciplina.horas_aula) FROM disciplina, aula AS aula, frequencia as frequencia, aluno, 
vinculo, matricula
WHERE disciplina.codigo = aula.disciplina_codigo
AND aula.codigo = frequencia.aula_codigo
AND frequencia.vinculo_id = vinculo.id
AND vinculo.matricula_codigo = matricula.codigo
AND matricula.aluno_ra = aluno.ra
AND aula.data_ocorrida BETWEEN DATEADD(WEEK, -1, GETDATE()) AND GETDATE()
GROUP BY disciplina.horas_aula, aluno.nome_completo, disciplina.nome, aluno.ra, disciplina.codigo


CREATE FUNCTION relatorio_frequencia()
RETURNS @relatorio TABLE(
	id VARCHAR(100),
	aluno VARCHAR(200),
	disciplina VARCHAR(200),
	quantidade_faltas INT,
	total_faltas INT,
	status VARCHAR(100)
)
AS
BEGIN
	DECLARE @id VARCHAR(100), @aluno VARCHAR(200), 
	@ra VARCHAR(09), @disciplina VARCHAR(200), @quantidade_faltas INT,
	@total_faltas INT, @status VARCHAR(100), @porcentagem DECIMAL(4,2), @disciplina_codigo INT

	DECLARE csr CURSOR FOR 
		SELECT CONCAT(aluno.ra, disciplina.codigo) AS id, aluno.ra, aluno.nome_completo, disciplina.nome,  COUNT(aula.codigo) - disciplina.horas_aula,
		disciplina.codigo
		FROM disciplina, aula AS aula, frequencia as frequencia, aluno, 
		vinculo, matricula
		WHERE disciplina.codigo = aula.disciplina_codigo
		AND aula.codigo = frequencia.aula_codigo
		AND frequencia.vinculo_id = vinculo.id
		AND vinculo.matricula_codigo = matricula.codigo
		AND matricula.aluno_ra = aluno.ra
		AND aula.data_ocorrida BETWEEN DATEADD(WEEK, -1, GETDATE()) AND GETDATE()
		GROUP BY disciplina.horas_aula, aluno.nome_completo, disciplina.nome, aluno.ra, disciplina.codigo

		OPEN csr 

		FETCH NEXT FROM csr INTO @id, @ra, @aluno, @disciplina, @quantidade_faltas, @disciplina_codigo

		WHILE @@FETCH_STATUS = 0
		BEGIN
			SET @total_faltas = (SELECT COUNT(aula.codigo) * disciplina.horas_aula - COUNT(frequencia.horas_aula) FROM aluno, vinculo, 
			matricula, disciplina, frequencia, aula WHERE aluno.ra = matricula.aluno_ra 
			AND vinculo.matricula_codigo = matricula.codigo 
			AND aula.codigo = frequencia.aula_codigo AND aula.disciplina_codigo = disciplina.codigo
			AND vinculo.disciplina_codigo = disciplina.codigo AND vinculo.id = frequencia.vinculo_id 
			AND aluno.ra = @ra AND disciplina.codigo = @disciplina_codigo GROUP BY disciplina.horas_aula)

			SET @porcentagem = (
			SELECT CAST(COUNT(frequencia.horas_aula) AS DECIMAL(4,2)) / (COUNT(aula.codigo) * disciplina.horas_aula) 
			FROM aluno, vinculo, matricula, disciplina, frequencia, aula WHERE aluno.ra = matricula.aluno_ra AND vinculo.matricula_codigo = matricula.codigo 
			AND vinculo.disciplina_codigo = disciplina.codigo AND disciplina.codigo = aula.disciplina_codigo 
			AND vinculo.id = frequencia.vinculo_id AND aluno.ra = @ra AND disciplina.codigo = @disciplina_codigo GROUP BY disciplina.horas_aula
			)

			SET @status = (
				SELECT estatus = CASE WHEN @porcentagem > 0.75 THEN 'Aprovado'
				WHEN @porcentagem < 0.75 THEN 'Reprovado'
				END)

			INSERT INTO @relatorio(id, aluno,disciplina, quantidade_faltas, total_faltas, status) 
			VALUES (@id, @aluno, @disciplina, @quantidade_faltas, @total_faltas, @status)

			FETCH NEXT FROM csr INTO @id, @ra, @aluno, @disciplina, @quantidade_faltas, @disciplina_codigo
		END
		CLOSE csr
		DEALLOCATE csr
		RETURN
END

SELECT * FROM relatorio_frequencia()

DROP FUNCTION relatorio_frequencia


SELECT * FROM frequencia, vinculo, matricula
WHERE frequencia.vinculo_id = vinculo.id
AND vinculo.matricula_codigo = matricula.codigo
AND matricula.aluno_ra = '202323702'

SELECT * FROM aluno


SELECT COUNT(aula.codigo) * disciplina.horas_aula FROM disciplina, aula
WHERE disciplina.codigo = aula.disciplina_codigo
GROUP BY disciplina.horas_aula

SELECT aluno.ra, aluno.nome_completo, disciplina.nome, AVG(nota.nota) AS media FROM nota, vinculo,
matricula, aluno, avaliacao,disciplina
WHERE nota.vinculo_id = vinculo.id
AND nota.avaliacao_codigo = avaliacao.codigo
AND vinculo.matricula_codigo = matricula.codigo
AND aluno.ra = matricula.aluno_ra
AND avaliacao.codigo = nota.avaliacao_codigo
AND disciplina.codigo = avaliacao.disciplina_codigo
GROUP BY aluno.nome_completo, disciplina.nome, aluno.ra
