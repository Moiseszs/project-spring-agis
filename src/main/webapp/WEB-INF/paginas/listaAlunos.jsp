<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <head>

    </head>

    <body>
        <nav>
            <h3>AGIS</h3>
            <a href="/">Voltar</a>
        </nav>

        <div>
            <c:forEach var="aluno" items="${alunos}">
                <div>
                    <label>${aluno.nomeCompleto}  </label>
                    <a href="vinculo/${aluno.ra}">Vincular Disciplinas</a>
                    
                </div>
            </c:forEach>
        </div>
    </body>
</html>