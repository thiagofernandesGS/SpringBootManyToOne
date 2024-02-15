package com.provaN1.ThiagoResponsavel.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.provaN1.ThiagoResponsavel.modelo.Filho;
import com.provaN1.ThiagoResponsavel.modelo.Responsavel;
import com.provaN1.ThiagoResponsavel.repository.FilhoRepository;
import com.provaN1.ThiagoResponsavel.repository.ResponsavelRepository;




@Controller
//@RequestMapping("/responsavel")
public class ResponsavelController {
	
	@Autowired
	private ResponsavelRepository responsavelRepository;
	
	@Autowired
	private FilhoRepository filhoRepository;
	
	@GetMapping("/novo")
	public String adicionarResponsavel(Model model) {
		model.addAttribute("responsavel", new Responsavel());
		return "/publica-criar-responsavel";
	}

	@PostMapping("/salvar")
	public String salvarResponsavel(@Valid Responsavel responsavel, BindingResult result, 
				RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "/publica-criar-responsavel";
		}	
		responsavelRepository.save(responsavel);
		attributes.addFlashAttribute("mensagem", "Cadastro feito com sucesso!");
		return "redirect:/";
	}
	
	@RequestMapping("/")
	public String listarUsuario(Model model) {
		List<Filho> lista = filhoRepository.findAll();
		model.addAttribute("filhos", lista);
		return "/listar-filho";
	}
	
	
}
