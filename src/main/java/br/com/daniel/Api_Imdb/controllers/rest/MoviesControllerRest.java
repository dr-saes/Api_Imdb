package br.com.daniel.Api_Imdb.controllers.rest;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MoviesControllerRest {

	private RestTemplate restTemplate;

	@Autowired
	public MoviesControllerRest(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("/rest/top250")
	 public ListaDeMovies getTop250Filmes() throws FileNotFoundException {

        ResponseEntity<ListaDeMovies> response =
                this.restTemplate.getForEntity("https://imdb-api.com/en/API/Top250Movies/k_cd7hvpny", ListaDeMovies.class);

        PrintWriter writer = new PrintWriter("src/main/resources/templates/content.html");
        new HTMLGenerator(writer).generate(response.getBody());
        writer.close();

        return response.getBody();

	}

	record Movie(String title, String image, String year, String imDbRating, String rank, String crew){}
	
	
    record ListaDeMovies(List<Movie> items){}
}