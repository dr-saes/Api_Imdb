package br.com.daniel.Api_Imdb.controllers.rest;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.daniel.Api_Imdb.controllers.html.HTMLGenerator;
import br.com.daniel.Api_Imdb.services.ImdbApiClient;

@RestController
public class MoviesControllerRest {

	public static final String POST_SUCCESS = "Filme inserido aos favoritos com sucesso!";
	public static final String POST_FAIL = "Filme não foi encontrado na lista";

	private ListaDeMovies movies = new ListaDeMovies(new ArrayList<>());
	private ListaDeMovies favoritos = new ListaDeMovies(new ArrayList<>());

	private ImdbApiClient imdbApiClient;

	@Autowired
	public MoviesControllerRest(ImdbApiClient imdbApiClient) {
		this.imdbApiClient = imdbApiClient;
	}

	@GetMapping("/rest/top250")
	public ListaDeMovies getTop250Movies(@RequestParam(required = false) String title) throws FileNotFoundException {

		this.movies.items.clear();

		ListaDeMovies response = imdbApiClient.getListaTop250();

		if (title == null) {
			this.movies.items.addAll(response.items());
		} else {
			this.movies.items().addAll(response.items().stream().filter(movie -> movie.title.contains(title))
					.collect(Collectors.toList()));
		}

		PrintWriter writer = new PrintWriter("src/main/resources/templates/content.html");
		new HTMLGenerator(writer).generate(movies);

		writer.close();

		return movies;

	}

	@PostMapping("/rest/favoritos/{movieId}")
	public String saveFavorito(@PathVariable String movieId) throws Exception {

		if (this.movies.items.isEmpty()) {
			getTop250Movies(null);
		}

		Optional<Movie> movieOp = this.movies.items.stream().filter(movie -> movie.id().equalsIgnoreCase(movieId))
				.findFirst();

		if (movieOp.isPresent()) {
			this.favoritos.items.add(movieOp.get());
			return POST_SUCCESS;
		} else {
			return POST_FAIL;
		}

	}

	@GetMapping("/rest/favoritos")
	public ListaDeMovies getFavoritos() throws FileNotFoundException {

		if (!favoritos.items.isEmpty()) {
			PrintWriter writer = new PrintWriter("src/main/resources/templates/favoritos.html");
			new HTMLGenerator(writer).generate(favoritos);
			writer.close();
		}

		return favoritos;
	}

	public record Movie(String title, String year, String image, String imDbRating, String rank, String id, String crew) {
	}

	public record ListaDeMovies(List<Movie> items) {
	}

}