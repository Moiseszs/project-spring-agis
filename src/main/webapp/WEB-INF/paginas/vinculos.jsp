<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>

    </head>

    <body>

        <form action="/lista-vinculos" method="GET" target="_blank">
            <select name="ra">
                <c:forEach var="aluno" items="${alunos}">
                    <option value="${aluno.ra}" >${aluno.nomeCompleto}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Listar">
        </form>

        <form action="atualiza-vinculos" method="POST">
            
                <table>
                    <thead>
                        <th>Disciplina</th>
                        <th>Cursada</th>
                    </thead>
                    <tbody>
                        <c:forEach var="vinculo" items="${vinculos}">
                        <tr>
                            <td>${vinculo.disciplina.nome}</td>
                            <c:if test="${vinculo.cursada}">
                                <td><input type="checkbox" value="${vinculo.id}" name="ids" checked ></td>
                            </c:if>
                            <c:if test="${vinculo.cursada == false}">
                                <td><input type="checkbox" value="${vinculo.id}" name="ids"></td>
                            </c:if>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>                                
            <input type="submit" value="Atualizar">
        </form>
    </body>
</html>