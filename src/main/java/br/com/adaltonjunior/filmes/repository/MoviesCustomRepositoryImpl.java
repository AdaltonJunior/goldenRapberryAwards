package br.com.adaltonjunior.filmes.repository;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.adaltonjunior.filmes.model.MoviesResultVo;

public class MoviesCustomRepositoryImpl implements MoviesCustomRepository {

	@PersistenceContext
	public EntityManager entityManager;
	
	@Override
	public List<MoviesResultVo> findMax() {
		List<MoviesResultVo> listVo = new LinkedList<MoviesResultVo>();
		
		StringBuilder sqlBase = new StringBuilder();
		sqlBase.append(" SELECT m.PRODUCERS, ");
		sqlBase.append(" 		m.YEAR AS followingWin, ");
		sqlBase.append(" 		LAG(m.YEAR) OVER (PARTITION BY PRODUCERS ORDER BY m.YEAR) AS previousWin, ");
		sqlBase.append(" 		m.YEAR - LAG(m.YEAR) OVER (PARTITION BY PRODUCERS ORDER BY m.YEAR) as diff ");
		sqlBase.append(" FROM (select * from MOVIES where winner = TRUE order by PRODUCERS, YEAR DESC) m ");
		sqlBase.append(" WHERE m.WINNER = TRUE ");
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * ");
		sql.append(" FROM ("+sqlBase+") ");
		sql.append(" WHERE DIFF = ( ");
		sql.append(" 	SELECT MAX(DIFF) FROM ("+sqlBase+") ");
		sql.append(" ) ");
		sql.append(" ORDER BY DIFF ");
		
		Query q = entityManager.createNativeQuery(sql.toString());
		List<Object[]> listResult = q.getResultList();
		for (Object[] o : listResult) {
			String produce = (String) o[0];
			Integer followingWin = (Integer) o[1];
			Integer previousWin = (Integer) o[2];
			Integer diff = (Integer) o[3];
			
			MoviesResultVo vo = new MoviesResultVo();
			vo.setProducer(produce);
			vo.setFollowingWin(followingWin);
			vo.setPreviousWin(previousWin);
			vo.setInterval(diff);
			listVo.add(vo);
		}		
		
		return listVo;
	}

	@Override
	public List<MoviesResultVo> findMin() {
		List<MoviesResultVo> listVo = new LinkedList<MoviesResultVo>();
		
		StringBuilder sqlBase = new StringBuilder();
		sqlBase.append(" SELECT m.PRODUCERS, ");
		sqlBase.append(" 		m.YEAR AS followingWin, ");
		sqlBase.append(" 		LAG(m.YEAR) OVER (PARTITION BY PRODUCERS ORDER BY m.YEAR) AS previousWin, ");
		sqlBase.append(" 		m.YEAR - LAG(m.YEAR) OVER (PARTITION BY PRODUCERS ORDER BY m.YEAR) as diff ");
		sqlBase.append(" FROM (select * from MOVIES where winner = TRUE order by PRODUCERS, YEAR DESC) m ");
		sqlBase.append(" ORDER BY PRODUCERS, YEAR DESC ");
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * ");
		sql.append(" FROM ("+sqlBase+") ");
		sql.append(" WHERE DIFF = ( ");
		sql.append(" 	SELECT MIN(DIFF) FROM ("+sqlBase+") ");
		sql.append(" ) ");
		sql.append(" ORDER BY DIFF ");
		
		Query q = entityManager.createNativeQuery(sql.toString());
		List<Object[]> listResult = q.getResultList();
		for (Object[] o : listResult) {
			String produce = (String) o[0];
			Integer followingWin = (Integer) o[1];
			Integer previousWin = (Integer) o[2];
			Integer diff = (Integer) o[3];
			
			MoviesResultVo vo = new MoviesResultVo();
			vo.setProducer(produce);
			vo.setFollowingWin(followingWin);
			vo.setPreviousWin(previousWin);
			vo.setInterval(diff);
			listVo.add(vo);
		}		
		
		return listVo;
	}
	
}
