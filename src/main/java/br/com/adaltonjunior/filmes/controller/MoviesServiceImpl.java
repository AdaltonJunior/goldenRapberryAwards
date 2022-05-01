package br.com.adaltonjunior.filmes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.adaltonjunior.filmes.model.MoviesResultVo;
import br.com.adaltonjunior.filmes.repository.MoviesRepository;

public class MoviesServiceImpl implements MoviesService {

	@Autowired
	private MoviesRepository moviesRepository;
	
	@Override
	public List<MoviesResultVo> findMax() {
		return moviesRepository.findMax();
	}

	@Override
	public List<MoviesResultVo> findMin() {
		return moviesRepository.findMin();
	}
	
}