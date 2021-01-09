package dao;

public class Review {
	
	
	String userId;
	String movieId;
	double reviewScore;
	
	public Review(String userId2, String movieId2, double rating) {
		this.userId = userId2;
		this.movieId = movieId2;
		this.reviewScore = rating;
	}
	
}
