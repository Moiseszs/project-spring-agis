package com.agis.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agis.repository.AlunoRepository;
import com.agis.repository.AulaRepository;
import com.agis.repository.Connector;
import com.agis.forms.AtualizacaoVinculosForm;
import com.agis.forms.ChamadaForm;
import com.agis.forms.MatriculaForm;
import com.agis.forms.NotasForm;
import com.agis.forms.VinculoForm;
import com.agis.model.Aluno;
import com.agis.model.Aula;
import com.agis.model.Avaliacao;
import com.agis.model.Curso;
import com.agis.model.Disciplina;
import com.agis.model.Frequencia;
import com.agis.model.Matricula;
import com.agis.model.Nota;
import com.agis.model.Relatorio;
import com.agis.model.RelatorioNotas;
import com.agis.model.Vinculo;
import com.agis.repository.CursoRepository;
import com.agis.repository.DisciplinaRepository;
import com.agis.repository.FrequenciaRepository;
import com.agis.repository.MatriculaRepository;
import com.agis.repository.NotaRepository;
import com.agis.repository.RelatorioNotasRepository;
import com.agis.repository.RelatorioRepository;
import com.agis.repository.VinculoRepository;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;



@Controller
public class AlunoController {
    
    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    VinculoRepository vinculoRepository;

    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    RelatorioRepository relatorioRepository;

    @Autowired
    FrequenciaRepository frequenciaRepository;

    @Autowired
    NotaRepository notaRepository;

    @Autowired
    RelatorioNotasRepository relatorioNotasRepository;

    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("var", 5);
        return "index";
    }


    @GetMapping("/matricula")
    public String getMatricula(Model model){
        List<Curso> cursos = cursoRepository.findAll();
        model.addAttribute("cursos", cursos);
        return "matricula";
    }

    @PostMapping("/matricula")
    public String matricula(@ModelAttribute MatriculaForm form, Model model){
        Aluno aluno = form.getAluno();
        
        try {
            validaCpf(aluno.getCpf());
            validaIdade(aluno.getDataNascimento());
        } 
        catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            return "matricula";
        }

        aluno.setAnoIngresso();
        aluno.setSemestreIngresso();
        aluno.setRa(geraRa(aluno));
        aluno = alunoRepository.save(aluno);
        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        Curso curso = cursoRepository.getReferenceById(form.getCodigoCurso());
        matricula.setCurso(curso);
        matriculaRepository.save(matricula);

        return "index";
    }

    @GetMapping("/lista-ra")
    public String listaAlunosVinculo(Model model){
        List<Aluno> alunos = alunoRepository.findAll();
        model.addAttribute("alunos", alunos);                
        return "listaAlunos";        
    }


    @GetMapping("/vinculo/{ra}")
    public String getVinculo(@PathVariable(value="ra") String ra, Model model){
        try {
            Aluno aluno = alunoRepository.getReferenceById(ra);
            List<Disciplina> disciplinas = disciplinaRepository.findDisciplinasDisponiveis(ra);
            model.addAttribute("disciplinas", disciplinas);
            model.addAttribute("aluno", aluno);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        }
        
        return "vinculo";
    }

    @PostMapping("/vinculo")
    public String postVinculo(@ModelAttribute VinculoForm vinculoForm, Model model){
        try {
            List<Integer> codigos = vinculoForm.getCodigos();
            List<Disciplina> disciplinas = new ArrayList<Disciplina>();
            Aluno aluno = alunoRepository.getReferenceById(vinculoForm.getRa());
            for(Integer codigo : codigos){
                disciplinas.add(disciplinaRepository.getReferenceById(codigo));
            }
            validaDisciplinas(disciplinas);
            for(Disciplina disciplina : disciplinas){
                Vinculo vinculo = new Vinculo();
                vinculo.setDisciplina(disciplina);
                vinculo.setMatricula(aluno.getMatricula());
                vinculoRepository.save(vinculo);         
            }            
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            return "vinculo";
        }
        
        return "index";
    }


    @GetMapping("/vinculos")
    public String getPaginaVinculos(Model model){
        List<Aluno> alunos = alunoRepository.findAll();   
        model.addAttribute("alunos", alunos);     
        return "vinculos";
    }

    @GetMapping("/lista-vinculos")
    public String getVinculos(@RequestParam("ra") String ra, Model model){
        Aluno aluno = alunoRepository.getReferenceById(ra);
        List<Vinculo> vinculos = aluno.getMatricula().getVinculos();
        model.addAttribute("vinculos", vinculos);        
        return "vinculos";        
    }


    @GetMapping("/relatorio-notas")
    public String getNotas(Model model){
        List<RelatorioNotas> relatorios = relatorioNotasRepository.findAll();
        model.addAttribute("relatorios", relatorios);        
        return "relatorioNotas";
    }


    @PostMapping("atualiza-vinculos")
    public String atualizaVinculos(@ModelAttribute AtualizacaoVinculosForm atualizacaoVinculosForm){
        for(Integer id : atualizacaoVinculosForm.getIds()){
            Vinculo vinculo = vinculoRepository.getReferenceById(id);
            System.out.println(vinculo.getCursada());
            vinculo.setCursada(true);
            vinculoRepository.save(vinculo);            
        }        
        return "redirect:/";
    }
    
    @GetMapping("/chamada")
    public String getPaginaChamada(Model model){
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        LocalDate dataAtual = LocalDate.now();
        model.addAttribute("dataAtual", dataAtual);
        model.addAttribute("disciplinas", disciplinas);                        
        return "chamada";
    }

    @GetMapping("/lista-alunos")
    public String getDadosChamada(@ModelAttribute("codigo") Integer codigo, Model model){
        Disciplina disciplina = disciplinaRepository.getReferenceById(codigo);
        List<Aluno> alunos = new ArrayList<Aluno>();
        for(Vinculo vinculo : disciplina.getVinculos()){
            alunos.add(vinculo.getMatricula().getAluno());                        
        } 
        LocalDate dataAtual = LocalDate.now();
        model.addAttribute("dataAtual", dataAtual);
        model.addAttribute("disciplina", disciplina);       
        model.addAttribute("alunos", alunos);
        
        return "chamada";
    }

    @PostMapping("/chamada")
    public String postChamada(@ModelAttribute ChamadaForm chamadaForm){
        Aula aula = new Aula();
        Disciplina disciplina = disciplinaRepository.getReferenceById(chamadaForm.getCodigoDisciplina());
        aula.setDisciplina(disciplina);
        aula.setDataOcorrida(chamadaForm.getDataOcorrida());
        aula = aulaRepository.save(aula);
        for(int i = 0; i < chamadaForm.getPresencas().size(); i++){
            Frequencia frequencia = new Frequencia();
            frequencia.setAula(aula);
            frequencia.setHorasAula(chamadaForm.getPresencas().get(i));
            frequencia.setVinculo(disciplina.getVinculos().get(i));   
            frequenciaRepository.save(frequencia);
        }     
        return "redirect:/";
    }

    @GetMapping("/relatorio")
    public String getPaginaRelatorio(Model model){
        List<Relatorio> relatorios = relatorioRepository.findAll();        
        model.addAttribute("relatorios", relatorios);
        return "relatorio";                
    }    


    @GetMapping("/relatorio-impresso")    
    public ResponseEntity getRelatorio(){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("param1", "texto");
        byte[] dados = null;
        String erro = "";
        InputStreamResource resource = null;
        HttpStatus status = null;
        HttpHeaders header = new HttpHeaders();
        try {
            Connection connection = Connector.connect();
            File arquivo = ResourceUtils.getFile("classpath:RelatorioFrequencias.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(arquivo.getAbsolutePath());
            dados = JasperRunManager.runReportToPdf(report, parameters, connection);                     
        } 
        catch (Exception e) {
            erro = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
            System.out.println(e.getMessage());
        }
        finally{
            if(erro.equals("")){
                InputStream stream = new ByteArrayInputStream(dados);
                resource = new InputStreamResource(stream);
                header.setContentLength(dados.length);
                header.setContentType(MediaType.APPLICATION_PDF);
                status = HttpStatus.OK;                            
            }
        }    
        return new ResponseEntity(resource, header, status);
    }

    @GetMapping("/relatorio-notas-impresso")
    public ResponseEntity getRelatorioNotas(){
        Map<String, Object> parameters = new HashMap<String, Object>();
        byte[] dados = null;
        String erro = "";
        InputStreamResource resource = null;
        HttpStatus status = null;
        HttpHeaders header = new HttpHeaders();
        try {
            Connection connection = Connector.connect();
            File arquivo = ResourceUtils.getFile("classpath:RelatorioNotas.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(arquivo.getAbsolutePath());
            dados = JasperRunManager.runReportToPdf(report, parameters, connection);                     
        } 
        catch (Exception e) {
            erro = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
            System.out.println(e.getMessage());
        }
        finally{
            if(erro.equals("")){
                InputStream stream = new ByteArrayInputStream(dados);
                resource = new InputStreamResource(stream);
                header.setContentLength(dados.length);
                header.setContentType(MediaType.APPLICATION_PDF);
                status = HttpStatus.OK;   
            }                         
            }
            return new ResponseEntity(resource, header, status);
    }

    @GetMapping("/notas")
    public String getPaginaNotas(Model model){
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        model.addAttribute("disciplinas", disciplinas);        
        return "notas";
    }

    @GetMapping("/dados-notas")
    public String getDadosDisciplina(@RequestParam("codigo") int codigo, Model model){
        Disciplina disciplina = disciplinaRepository.getReferenceById(codigo);
        List<Avaliacao> avaliacaos = disciplina.getAvaliacaos();
        List<Vinculo> vinculos = disciplina.getVinculos();
        List<Aluno> alunos = new ArrayList<Aluno>();
        for(Vinculo vinculo : vinculos){
            Aluno aluno = vinculo.getMatricula().getAluno();
            alunos.add(aluno);            
        }        
        model.addAttribute("avaliacaos", avaliacaos);
        model.addAttribute("disciplina", disciplina);
        model.addAttribute("alunos", alunos);
        return "notas";        
    }

    @PostMapping("/notas")
    public String postNotas(@ModelAttribute NotasForm form){
        Disciplina disciplina = disciplinaRepository.getReferenceById(form.getCodigoDisciplina());
        List<Vinculo> vinculos = disciplina.getVinculos();
            for(int i = 0; i < disciplina.getVinculos().size(); i++){
                Nota nota1 = new Nota();
                nota1.setVinculo(vinculos.get(i));
                nota1.setNota(form.getAvaliacao0().get(i));
                nota1.setAvaliacao(disciplina.getAvaliacaos().get(0));
                notaRepository.save(nota1);

                Nota nota2 = new Nota();
                nota2.setVinculo(vinculos.get(i));
                nota2.setNota(form.getAvaliacao1().get(i));
                nota2.setAvaliacao(disciplina.getAvaliacaos().get(1));
                notaRepository.save(nota2);

                if(disciplina.getAvaliacaos().size() >= 3){
                    Nota nota3 = new Nota();
                    nota3.setVinculo(vinculos.get(i));
                    nota3.setNota(form.getAvaliacao2().get(i));
                    nota3.setAvaliacao(disciplina.getAvaliacaos().get(2));
                    notaRepository.save(nota3);
                }
                
            }                                                                
        return "redirect:/";        
    }

    private String geraRa(Aluno aluno){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(aluno.getAnoIngresso());
        stringBuilder.append(aluno.getSemestreIngresso());
        for(int i = 0; i < 4; i++){
            int min = 0;
            int max = 10;
            int numeroAleatorio = (int)Math.floor(Math.random() * (max - min) + min);
            stringBuilder.append(numeroAleatorio);
        }
        return stringBuilder.toString();
    }

    public void validaCpf(String cpf) throws Exception {
		if(cpf.length() <= 0 | cpf.length() > 11) {
			throw new Exception("CPF com caracteres insuficientes");
		}
		else {
			int primeiroDigitoVerificador = Character.getNumericValue(cpf.charAt(9));
			int segundoDigitoVerificador = Character.getNumericValue(cpf.charAt(10));
			int primeiraSoma = somaDigitos(cpf, 9);
			int segundaSoma = somaDigitos(cpf, 10);
			int primeiroRestante = (primeiraSoma * 10) % 11;
			int segundoRestante = (segundaSoma * 10) % 11;
			if(primeiroRestante == primeiroDigitoVerificador) {
				if(segundoRestante == segundoDigitoVerificador) {
					
				}
			}
            else{
                throw new Exception("CPF Invalido");
            }
		}
	}

    public int somaDigitos(String cpf, int quantidade) {
		int soma = 0;
		for(int i = 0, j = quantidade + 1 ; i <= quantidade && j >= 2; j--, i++) {
			soma = ((Character.getNumericValue(cpf.charAt(i)) * j) + soma);
		}
		return soma;
	}

    public void validaIdade(LocalDate dataNascimento) throws Exception{
		if(dataNascimento.compareTo(LocalDate.now()) <= -18) {
		}
        else{
            throw new Exception("Idade inferior a 18 anos");
        }
	}

    public void validaDisciplinas(List<Disciplina> disciplinas) throws Exception{
        for(int i = 0; i < disciplinas.size() - 1; i++){
            if(!disciplinas.get(i).getDiaSemana().equals(disciplinas.get(i+1).getDiaSemana())){
                if(!(disciplinas.get(i).getHoraComeco().equals(disciplinas.get(i+1).getHoraComeco()))){

                }
            }
            else{
                throw new Exception("Disciplinas em conflito");
            }
        }                        
    }
}

