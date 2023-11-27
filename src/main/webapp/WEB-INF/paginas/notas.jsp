<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    </head>

    <body>
        <nav>
            AGIS
            <a href="/">Voltar</a>
        </nav>

        <form action="/dados-notas" target="_blank">
            <select name="codigo">
                <c:forEach var="disciplina" items="${disciplinas}">
                    <option value="${disciplina.codigo}">${disciplina.nome}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Pesquisar">
        </form>

        <form action="notas" method="POST">
            <input type="hidden" name="codigoDisciplina" value="${disciplina.codigo}">
            <table>
                <thead>
                    <c:if test="${not empty alunos}">
                        <th>Nome do Aluno</th>
                    </c:if>
                    
                    <c:forEach var="avaliacao" items="${avaliacaos}">
                        <th>${avaliacao.nome}</th>
                    </c:forEach>
                </thead>
                <tbody>
                    <c:forEach var="aluno" items="${alunos}">
                        
                        <tr>
                            <td>${aluno.nomeCompleto}</td>
                            <c:forEach var="avaliacao" items="${avaliacaos}" varStatus="loop">
                                <td><input type="number" step="any" name="avaliacao${loop.index}"></td>
                            </c:forEach>
                        </tr>                                                
                    </c:forEach>
                </tbody>
            </table>
            <input type="submit" value="Inserir">
        </form>
    </body>
</html>
