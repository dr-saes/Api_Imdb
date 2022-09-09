package br.com.daniel.Api_Imdb.controllers.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MoviesControllerHtml {

	public MoviesControllerHtml() {
	}

	@GetMapping("/top250")
	public String top250() {

		return "top250";

	}

}
