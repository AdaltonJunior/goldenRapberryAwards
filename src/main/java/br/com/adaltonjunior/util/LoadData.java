package br.com.adaltonjunior.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import br.com.adaltonjunior.filmes.model.Movies;
import br.com.adaltonjunior.filmes.repository.MoviesRepository;

@Component
public class LoadData {
	
	@Value( "${app.filepath}" )
	private String FILE_PATH;
	
	private final String COMMA_SEPARATOR = ";";
	
	// Coll Index
	private final int YEAR = 0;
	private final int TITLE = 1;
	private final int STUDIOS = 2;
	private final int PRODUCERS = 3;
	private final int WINNER = 4;
	
	@Autowired
	private MoviesRepository moviesRepository;
	
	private static final Logger log = LoggerFactory.getLogger(LoadData.class);
	
	public void readDataFromFile() throws IOException {
		File file = ResourceUtils.getFile(FILE_PATH);
		log.info("Tentando ler arquivo: " + file.getAbsolutePath());
		if(!file.exists()) {
			log.error("Arquivo não encontrado!");
			throw new IllegalStateException("Arquivo nao encontrado. O Arquivo deve ter o nome 'movielist.csv' e estar no mesmo diretorio do jar.");
		}
		
		Path path = Paths.get(file.getPath());
		log.info("Arquivo importado com sucesso!");
		log.info("");
		
		try(BufferedReader reader = Files.newBufferedReader(path)){
			int linha = 1;
			
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] lineColumns = line.split(COMMA_SEPARATOR);
				
				// Se for a linha do cabeçalho ele pula.
				if(lineColumns[YEAR].startsWith("year")) continue;
				
				try {
					String year = lineColumns.length > 0 ? lineColumns[YEAR] : null;
					String title = lineColumns.length > 1 ? lineColumns[TITLE] : "";
					String studios = lineColumns.length > 2 ? lineColumns[STUDIOS] : "";
					String producersFromLine = lineColumns.length > 3 ? lineColumns[PRODUCERS] : "";
					String winner = lineColumns.length > 4 ? lineColumns[WINNER] : "no";
					
					List<String> listProducers = new ArrayList<String>();
					if((producersFromLine.toUpperCase().contains("AND") || producersFromLine.contains(",")) && !splitProducers(producersFromLine).isEmpty()) {
						listProducers.addAll(splitProducers(producersFromLine));
					}else {
						listProducers.add(producersFromLine);
					}
					
					for (String producer : listProducers) {
						Movies movie = new Movies();
						movie.setYear(year != null ? Integer.parseInt(lineColumns[YEAR]) : null);
						movie.setTitle(title);
						movie.setStudios(studios);
						movie.setProducers(producer);
						movie.setWinner("yes".equalsIgnoreCase(winner));
						moviesRepository.save(movie);
					}					
					linha++;
				}catch(Exception e) {
					log.error("Ocorreu um erro ao importar a linha "+ linha + ", a mesma será desconsiderada!");
				}
			}
		}catch (IOException e) {
			throw new IllegalStateException("O arquivo não pode ser aberto!");
		}
	}
	
	private List<String> splitProducers(String producers){
		List<String> listProducers = new LinkedList<String>();
		String[] produc = producers.split("(,)|(AND)|(and)");
		for (String string : produc) {
			listProducers.add(string.trim());
		}
		return listProducers;
	}
}
