package br.com.daniel.Api_Imdb.controllers.rest.service;

import java.io.PrintWriter;

import br.com.daniel.Api_Imdb.controllers.rest.MoviesControllerRest;


public class HTMLGenerator {

    private final PrintWriter writer;

    public HTMLGenerator(PrintWriter writer) {
        this.writer = writer;
    }

    String head =
    """
   <html>	
    <head>
		<meta charset="UTF-8"/>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link href="https://fonts.googleapis.com/css?family=Handlee&display=swap" rel="stylesheet">
		
    </head>
     """;
    		
    		
    String divTemplate =
    		"""
    		<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
                <h4 class=\"card-header\">%s</h4>
                 <h5 class=\"card-header\">%s</h5>
                <div class=\"card-body\">
                    <img class=\"card-img\" src=\"%s\" alt=\"%s\">
                    <p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
                </div>
            </div>
	</body>
</html>
    				
    				
            """;
    
    

    public void generate(MoviesControllerRest.ListaDeMovies movies) {

        writer.println(head);

        writer.println("<body>");

        movies.items().forEach(movie -> {
            writer.println(String.format(divTemplate, movie.title(), movie.rank(), movie.image(), movie.title(), movie.imDbRating(), movie.year()));
        });
        writer.println(
                """
                    </body>
                </html>
                """);
    }




}