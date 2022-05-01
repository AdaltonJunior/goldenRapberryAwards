package br.com.adaltonjunior;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.adaltonjunior.filmes.model.MoviesResultVo;
import br.com.adaltonjunior.filmes.model.MoviesVo;

@ActiveProfiles("test")
@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InicializadorIT {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int port;
	
	@Test
	@DisplayName("TESTE -> Buscar vencedores com maior e menor intervalo entre os premios")
	void buscarMaxVencedor() {
		ResponseEntity<MoviesVo> response = testRestTemplate.exchange("/awards/statistics", HttpMethod.GET, null, new ParameterizedTypeReference<MoviesVo>() {});
		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		
		MoviesVo body = response.getBody();
		Assertions.assertNotNull(body);
		
		// Menor intervalo
		Assertions.assertNotNull(body.getMin());
		Assertions.assertTrue(body.getMin().size() == 1);
		
		MoviesResultVo emMenorTempo = body.getMin().get(0);
		Assertions.assertEquals("PRODUCER 1", emMenorTempo.getProducer());
		Assertions.assertEquals(3, emMenorTempo.getInterval());
		Assertions.assertEquals(2018, emMenorTempo.getFollowingWin());
		Assertions.assertEquals(2015, emMenorTempo.getPreviousWin());
		
		// Maior intervalo
		Assertions.assertNotNull(body.getMax());
		Assertions.assertTrue(body.getMax().size() == 1);
		
		MoviesResultVo emMaiorTempo = body.getMax().get(0);
		Assertions.assertEquals("PRODUCER 2", emMaiorTempo.getProducer());
		Assertions.assertEquals(9, emMaiorTempo.getInterval());
		Assertions.assertEquals(2018, emMaiorTempo.getFollowingWin());
		Assertions.assertEquals(2009, emMaiorTempo.getPreviousWin());
	}

}
