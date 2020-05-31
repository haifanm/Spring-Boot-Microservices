package microservices.spring.boot.ratingsdataservice.resources;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.spring.boot.ratingsdataservice.models.Rating;
import microservices.spring.boot.ratingsdataservice.models.UserRating;


@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(
				new Rating("movie1",1),
				new Rating("movie2",2),
				new Rating("movie3",3)
		);
		
		UserRating userRating = new UserRating();
		userRating.setUserRatings(ratings);
		
		return userRating;
	}
	
}
