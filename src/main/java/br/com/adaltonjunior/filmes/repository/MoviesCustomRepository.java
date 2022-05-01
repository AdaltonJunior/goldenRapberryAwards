package br.com.adaltonjunior.filmes.repository;

import java.util.List;

import br.com.adaltonjunior.filmes.model.MoviesResultVo;

public interface MoviesCustomRepository {
	
	public List<MoviesResultVo> findMax();
	
	public List<MoviesResultVo> findMin();
	
}
