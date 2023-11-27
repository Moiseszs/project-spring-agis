<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <head>

    </head>

    <body>
        <nav>
            <h3>AGIS</h3>
        </nav>
        <h4>Relatorio de Faltas por Semana</h4>
        <table >
            <thead>
                <th>Aluno</th>
                <th>Disciplina</th>
                <th>Quantidade de Faltas</th>
                <th>Total de Faltas</th>
            </thead>
            <tbody>
                <c:forEach var="relatorio" items="${relatorios}">
                    <tr>
                        <td>${relatorio.nomeCompleto}</td>
                        <td>${relatorio.disciplinaNome}</td>
                        <td>${relatorio.media}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>    
        <form action="relatorio-notas-impresso" target="_blank">
            <input type="submit" value="Gerar Relatorio">
        </form>            
    </body>
</html>