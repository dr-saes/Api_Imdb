package br.com.daniel.Api_Imdb.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class MoviesControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveriaDevolver200EUmCorpoNaoNulo() throws Exception {
		
		URI uri = new URI("/top250");
		
		mockMvc .perform(MockMvcRequestBuilders.get(uri))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse();
				
	}

}
