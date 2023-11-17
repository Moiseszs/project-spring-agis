package com.agis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.agis.AlunoRepository;
import com.agis.model.Aluno;

@Controller
public class AlunoController {
    
    @Autowired
    AlunoRepository alunoRepository;


    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("var", 5);
        return "index";
    }

    public String novoAluno(Model model){
        Aluno aluno = new Aluno();
        alunoRepository.save(aluno);
        return "index";
    }
}
