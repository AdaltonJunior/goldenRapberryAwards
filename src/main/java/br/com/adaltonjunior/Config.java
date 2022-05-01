package br.com.adaltonjunior;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.adaltonjunior.filmes.controller.MoviesService;
import br.com.adaltonjunior.filmes.controller.MoviesServiceImpl;
import br.com.adaltonjunior.filmes.repository.MoviesCustomRepository;
import br.com.adaltonjunior.filmes.repository.MoviesCustomRepositoryImpl;

@Configuration
public class Config {
	
	@Bean
	public MoviesService getMovieService() {
		return new MoviesServiceImpl();
	}
	
	@Bean
	public MoviesCustomRepository getMoviesCustomRepository() {
		return new MoviesCustomRepositoryImpl();
	}
	
}
