package microservices.spring.boot.moviecatalogservice.resources;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import microservices.spring.boot.moviecatalogservice.models.CatalogItem;
import microservices.spring.boot.moviecatalogservice.models.Movie;
import microservices.spring.boot.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
				
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);
		
		return ratings.getUserRatings().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getMovieName(), "Test" , rating.getRating());
		
		})
		.collect(Collectors.toList());
		
	}
}
