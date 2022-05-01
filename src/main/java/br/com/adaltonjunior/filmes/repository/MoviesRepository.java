package br.com.adaltonjunior.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adaltonjunior.filmes.model.Movies;

public interface MoviesRepository extends JpaRepository<Movies, Long>, MoviesCustomRepository {
	
}
