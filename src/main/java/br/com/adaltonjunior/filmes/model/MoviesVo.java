package br.com.adaltonjunior.filmes.model;

import java.util.List;

public class MoviesVo {
	
	private List<MoviesResultVo> min;
	private List<MoviesResultVo> max;
	
	public MoviesVo() {
		// TODO Auto-generated constructor stub
	}

	public List<MoviesResultVo> getMin() {
		return min;
	}

	public void setMin(List<MoviesResultVo> min) {
		this.min = min;
	}

	public List<MoviesResultVo> getMax() {
		return max;
	}

	public void setMax(List<MoviesResultVo> max) {
		this.max = max;
	}
	
}
