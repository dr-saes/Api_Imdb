package br.com.daniel.Api_Imdb.controllers.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.daniel.Api_Imdb.controllers.rest.MoviesControllerRest;

@Service
public class ImdbApiClient {

	@Value("${imdb.apikey}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

	public MoviesControllerRest.ListaDeMovies getBody() {
		
		ResponseEntity<MoviesControllerRest.ListaDeMovies> response =
                this.restTemplate.getForEntity("https://imdb-api.com/en/API/Top250Movies/"+apiKey, MoviesControllerRest.ListaDeMovies.class);

		return response.getBody();
	}


}