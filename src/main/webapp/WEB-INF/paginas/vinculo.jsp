<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<body>
    <nav>
        AGIS
        <a href="/">Voltar</a>
    </nav>
    <div>
        <label>Aluno: </label>
        <input type="text" value="${aluno.nomeCompleto}" disabled>
    </div>
    <div>
        <c:out value="${erro}"></c:out>
    </div>
    <div>
        <h4>Escolha as disciplinas para vincular</h4>
        <form action="/vinculo" method="POST" target="_blank">
            <div class="disciplinas">
                <input type="hidden" name="ra" value="${aluno.ra}">
                <c:forEach var="disciplina" items="${disciplinas}">
                    <c:out value="${disciplina.nome}"></c:out>
                    <input type="checkbox" name="codigos" value="${disciplina.codigo}">
                    <div>
                        <label>Dia da semana: </label>
                        <c:out value="${disciplina.diaSemana}"></c:out>
                    </div>
                    <div>
                        <label>Hora de Começo: </label>
                        <c:out value="${disciplina.horaComeco}"></c:out>
                    </div>
                    
                </c:forEach>
            </div>
            <input type="submit" value="Adicionar">
        </form>
        
        
    </div>

    
</body>
</html>