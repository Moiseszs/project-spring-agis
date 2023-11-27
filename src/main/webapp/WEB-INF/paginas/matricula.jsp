<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    </head>

    <body>
        <nav>AGIS</nav>

        <main>
            <form action="matricula" method="POST">
                <div>
                    <label>Nome Completo:</label>
                    <input type="text" name="aluno.nomeCompleto">
                </div>
                <div>
                    <label>CPF:</label>
                    <input type="text" name="aluno.cpf">
                </div>
                <div>
                    <label>Data de Nascimento: </label>
                    <input type="date" name="aluno.dataNascimento">
                </div>
                <div>
                    <label>Instituição de Segundo Grau:</label>
                    <input type="text" name="aluno.instituicaoSegundoGrau">
                </div>
                <div>
                    <label>Ano de Graduação do Segundo Grau:</label>
                    <input type="number" name="aluno.anoFormacao">
                </div>
                <div>
                    <label>Curso:</label>
                    <select name="codigoCurso">
                        <c:forEach var="curso" items="${cursos}">
                            <option value="${curso.codigo}"><c:out value="${curso.nome}"></c:out></option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <input type="submit" value="Cadastrar">
                </div>
            </form>
        </main>
        <c:out value="${erro}"></c:out>
    </body>

</html>