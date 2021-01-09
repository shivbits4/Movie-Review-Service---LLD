package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Movie {
	
	
	String movieId;
	String movieName;
	Date releaseDate;
	HashMap<UserStatus, List<Review>> reviews;
	HashMap<String, Review> reviewsByUserId;
	Genre genre;
	double avgScore;

	public Movie(String movieName,Date releDate, Genre genre) {
		
		this.movieId = UUID.randomUUID().toString();
		this.movieName = movieName;
		this.releaseDate = releDate;
		this.genre = genre;
		this.avgScore = 0.0;
		reviews = new HashMap<UserStatus, List<Review>>();
		reviewsByUserId = new HashMap<String, Review>();
	}
	
	
	public HashMap<String, Review> getReviewsByUserId() {
		return reviewsByUserId;
	}

	public HashMap<UserStatus, List<Review>> getReviews() {
		return reviews;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public void addReview(User user, Review review) {
		
		if(reviews.containsKey(user.getUserStatus())) {
			reviews.get(user.getUserStatus()).add(review);
		} else {
			List<Review> list = new ArrayList<Review>();
			list.add(review);
			reviews.put(user.getUserStatus(), list);
		}
		
		updateAverageMovieScore(review.reviewScore, reviewsByUserId.size());
		reviewsByUserId.put(user.getUserId(), review);
		
		
	}
	
	private void updateAverageMovieScore(double review, int size) {
		
		double currAvg = avgScore*(size);
		currAvg += review;
		
		this.avgScore = currAvg/(size+1);
	}


	public double getAverageReviewScore() {
		
		return avgScore;
	}

	


}
