package br.com.adaltonjunior.filmes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.adaltonjunior.filmes.model.MoviesVo;

@RestController
@RequestMapping("awards")
public class MoviesController {
	
	@Autowired
	private MoviesService service;
	
	@ResponseBody
	@GetMapping("statistics")
	public MoviesVo getPioresFilmes(){
		MoviesVo result = new MoviesVo();
		result.setMax(service.findMax());
		result.setMin(service.findMin());
		return result;
	}
	
}