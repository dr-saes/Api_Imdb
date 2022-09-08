package br.com.daniel.Api_Imdb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImdbMoviesController {

	public ImdbMoviesController() {
	}

	@GetMapping("/top250")
	public String top250() {

		return "content";

	}

}
