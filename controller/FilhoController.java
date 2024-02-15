package com.provaN1.ThiagoResponsavel.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.provaN1.ThiagoResponsavel.modelo.Filho;
import com.provaN1.ThiagoResponsavel.modelo.Responsavel;
import com.provaN1.ThiagoResponsavel.repository.FilhoRepository;
import com.provaN1.ThiagoResponsavel.repository.ResponsavelRepository;

@Controller
@RequestMapping("/filho")
public class FilhoController {
	
	@Autowired
	private FilhoRepository filhoRepository;
	
	@Autowired
	private ResponsavelRepository responsavelRepository;
	
	@GetMapping("/novo")
	public String adicionarFilho(Model model) {
		model.addAttribute("filho", new Filho());
		List<Responsavel> responsaveis = responsavelRepository.findAll();
		model.addAttribute("responsaveis", responsaveis);
		return "/publica-criar-filho";
	}
	
	@PostMapping("/salvar")
	public String salvarFilho(@Valid Filho filho, BindingResult result, 
				RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "/publica-criar-filho";
		}	
		filhoRepository.save(filho);
		attributes.addFlashAttribute("mensagem", "Cadastro feito com sucesso!");
		return "redirect:/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarResponsavel(@PathVariable("id") long id, Model model) {
		Optional<Filho> filhoVelho = filhoRepository.findById(id);
		if (!filhoVelho.isPresent()) {
			new IllegalArgumentException("Id inválido: " + id);
		}

		Filho filho = filhoVelho.get();
		model.addAttribute("filho", filho);
		return "/";
	}
	
	@PostMapping("/editar/{id}")
	public String editarResponsavel(@PathVariable("id") long id, @Valid Filho filho, BindingResult result) {
		if (result.hasErrors()) {
			filho.setId(id);
			return "/";
		}
		filhoRepository.save(filho);
		return "/";
	}

	
	@GetMapping("/apagar/{id}")
	public String deletarResponsavel(@PathVariable("id") long id, Model model) {
		Filho filho = filhoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));
		filhoRepository.delete(filho);
		return "/";
	}


}
	