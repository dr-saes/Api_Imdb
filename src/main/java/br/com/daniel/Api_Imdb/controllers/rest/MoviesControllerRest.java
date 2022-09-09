package br.com.daniel.Api_Imdb.controllers.rest;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.daniel.Api_Imdb.controllers.rest.service.HTMLGenerator;
import br.com.daniel.Api_Imdb.controllers.rest.service.ImdbApiClient;

@RestController
public class MoviesControllerRest {

	private ListaDeMovies movies = new ListaDeMovies(new ArrayList<>());
	private ImdbApiClient imdbApiClient;

	@Autowired
	public MoviesControllerRest(ImdbApiClient imdbApiClient) {
		this.imdbApiClient = imdbApiClient;
	}
	
	@GetMapping("/rest/top250")
	public ListaDeMovies getTop250Filmes(@RequestParam(required = false) String title) throws FileNotFoundException {

		ListaDeMovies response = imdbApiClient.getBody();

		if(title == null ) {
			this.movies.items.addAll(response.items());   
		}else {
			this.movies.items().addAll(response.items().stream()
                    .filter(movie -> movie.title.contains(title))
                    .collect(Collectors.toList()));
		}
		
		PrintWriter writer = new PrintWriter("src/main/resources/templates/content.html");
		new HTMLGenerator(writer).generate(movies);
		
		writer.close();

		return movies;

	}

	public record Movie(String title, String year, String image, String imDbRating, String rank, String id,
			String crew) {
	}

	public record ListaDeMovies(List<Movie> items) {
	}

}
