import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="disciplina")
public class Disciplina {

    @Id
    private int codigoDisciplina;

    @Column(name="nome", length=150, nullable = false)
    private String nome;
    
    @Column(name="nome_professor", length=200, nullable = false)
    private String nomeProfessor;

    @Column(name="dia_semana", nullable = false)
    private int diaSemana;

    private int semestre;

    private int horasAula;

    @ManyToOne
    @JoinColumn(name="codigo", nullable = false)
    private Curso curso;

    @Column(name="hora_comeco")
    private Time horaComeco;

}
