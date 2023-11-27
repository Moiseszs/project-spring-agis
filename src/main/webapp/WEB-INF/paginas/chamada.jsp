<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>

    </head>

    <body>
        <form action="lista-alunos" method="GET" target="_blank">
            <label>Selecione uma disciplina: </label>
            <select name="codigo">
                <c:forEach var="disciplina" items="${disciplinas}">
                    <option value="${disciplina.codigo}">${disciplina.nome}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Selecionar">
        </form>                

        <form action="chamada" method="POST">
            <label>Data atual: </label>
            <input type="date" name="dataOcorrida" value="${dataAtual}" >
            <table>
                <thead>
                    <th>Aluno</th>
                    <th>Presença</th>
                </thead>
                <tbody>
                    <input type="hidden" name="codigoDisciplina" value="${disciplina.codigo}">
                    <c:forEach var="aluno" items="${alunos}">
                        <tr>
                            <td>${aluno.nomeCompleto}</td>
                            <td><input type="number" name="presencas" min="0" max="${disciplina.horasAula}" value="0"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="submit" value="Realizar Chamada">
        </form>
    </body>
</html>