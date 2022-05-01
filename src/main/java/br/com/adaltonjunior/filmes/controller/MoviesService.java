package br.com.adaltonjunior.filmes.controller;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adaltonjunior.filmes.model.MoviesResultVo;

@Service
public interface MoviesService {
	
	public List<MoviesResultVo> findMax();
	
	public List<MoviesResultVo> findMin();
	
}