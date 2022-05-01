package br.com.adaltonjunior;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.adaltonjunior.util.LoadData;

@SpringBootApplication
public class Inicializador {

	@Autowired private LoadData loadData;
	
	private static final Logger log = LoggerFactory.getLogger(Inicializador.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Inicializador.class, args);
	}
	
	@PostConstruct
    public void initData() throws Exception {
		log.info("");
		log.info("Iniciando Leitura do CSV");
		loadData.readDataFromFile();
	}

}
