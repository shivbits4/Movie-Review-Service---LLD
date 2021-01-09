package services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import dao.Genre;
import dao.Movie;
import dao.Review;
import dao.User;
import dao.UserStatus;
import exceptions.InvalidDate;
import exceptions.InvalidGenre;
import exceptions.InvalidReviewScore;
import exceptions.InvalidUser;
import utility.MovieServiceUtility;

public class MovieReviewService  implements Service{
	
	List<User> users;
	List<Movie> movies;
	HashMap<Genre, PriorityQueue<Movie>> moviesByGenre;
	HashMap<String, PriorityQueue<Movie>> moviesByYear;
	HashMap<UserStatus, PriorityQueue<Movie>> moviesByUserStatus;
	
	public MovieReviewService() {
		users = new ArrayList<User>();
		movies = new ArrayList<Movie>();
		moviesByGenre = new HashMap<Genre, PriorityQueue<Movie>>();
		moviesByYear = new HashMap<String, PriorityQueue<Movie>>();
		moviesByUserStatus = new HashMap<UserStatus, PriorityQueue<Movie>>();
	}

	@Override
	public boolean review(User user, Movie movie, double rating) 
			throws InvalidReviewScore, InvalidDate, InvalidUser {
		
		Date todayDate = new Date(System.currentTimeMillis());	
		
		if(MovieServiceUtility.checkValidDate(movie.getReleaseDate(), todayDate)) {		
			throw new InvalidDate("Release date should be less than current Date");
		}
		
		if(!MovieServiceUtility.checkValidUser(user.getUserId(), movie.getReviewsByUserId())) {
			throw new InvalidUser("This user can not review this movie again!!");
		}
		
		if(rating < 0 || rating > 10) {	
			throw new InvalidReviewScore("Review score should be between 0-10 both inclusive");		
		}
		
		Review review = new Review(user.getUserId(), movie.getMovieId(), rating);
		
		movie.addReview(user, review);
		
		user.incrementReview();		
		user.upgradeUser(MovieServiceUtility.upgradeUser(user.getNumberOfReviews()));
		
		System.out.println("Review added by "+ user.getFname() + " for movie " + movie.getMovieName());
		
		return true;
	}
	

	public boolean addUser(User user) {
		users.add(user);	
		return true;
	}
	
	public boolean addMovie(Movie movie) {
		
		movies.add(movie);
		addMoviesByGenre(movie);
		addMoviesByYear(movie);
		
		return true;
	}
	
	private boolean  addMoviesByGenre(Movie movie) {
		if(moviesByGenre.containsKey(movie.getGenre())){
			moviesByGenre.get(movie.getGenre()).add(movie);
		} else {
			PriorityQueue<Movie> list = new PriorityQueue<Movie>(new MovieComparator());
			list.add(movie);
			moviesByGenre.put(movie.getGenre(), list);
		}
		
		return true;
	}
	
	private boolean  addMoviesByYear(Movie movie) {
		
		String year = Integer.toString(MovieServiceUtility.getYearFromDate(movie.getReleaseDate()));
		
		if(moviesByYear.containsKey(year)){
			moviesByYear.get(year).add(movie);
		} else {
			PriorityQueue<Movie> list = new PriorityQueue<Movie>(new MovieComparator());
			list.add(movie);
			moviesByYear.put(year, list);
		}
		
		return true;
	}

	public double getAverageReviewScoreForYear(String year) throws InvalidDate {
		
		if(!moviesByYear.containsKey(year)) {
			throw new InvalidDate("Year not present");
		}
		
		
		double avgReviewScore = 0.0;
		PriorityQueue<Movie> movies = moviesByYear.get(year);
		
		for(Movie movie : movies) {
			avgReviewScore+= movie.getAverageReviewScore();
		}
		
		return (movies.size() > 0 ) ? avgReviewScore/movies.size() : 0.0;
		
	}
	
	public double getAverageReviewScoreForGenre(Genre genre) throws InvalidGenre {
		
		if(!moviesByGenre.containsKey(genre)) {
			throw new InvalidGenre("Genre not present");
		}
		
		
		double avgReviewScore = 0.0;
		PriorityQueue<Movie> movies = moviesByGenre.get(genre);
		
		for(Movie movie : movies) {
			avgReviewScore+= movie.getAverageReviewScore();
		}
		
		return (movies.size() > 0 ) ? avgReviewScore/movies.size() : 0.0;
		
	}
	
	public double getAverageReviewScoreForMovie(Movie movie) {
		return movie.getAverageReviewScore();
		
	}

	public List<Movie> topNMoviesByTotalReviewScore(int N, UserStatus userStatus, String year) throws InvalidDate {
		
		
		if(!moviesByYear.containsKey(year)) {
			throw new InvalidDate("Year not present");
		}
		
		PriorityQueue<Movie> queue = new PriorityQueue<Movie>(moviesByYear.get(year));
		int count = 0;
		List<Movie> list = new ArrayList<Movie>();
		while(!queue.isEmpty() && count <=N) {
			Movie movie = queue.poll();
			list.add(movie);
		}
		

		System.out.print("Top N movies in year");
		System.out.print(list.toString());
		return list;
	}
	
    public List<Movie> topNMoviesByTotalReviewScore(int N, UserStatus userStatus, Genre genre) throws InvalidGenre {
		
		if(!moviesByGenre.containsKey(genre)) {
			throw new InvalidGenre("Genre not present");
		}
		
    	
		PriorityQueue<Movie> queue = new PriorityQueue<Movie>(moviesByGenre.get(genre));
		int count = 0;
		List<Movie> list = new ArrayList<Movie>();
		while(!queue.isEmpty() && count <=N) {
			Movie movie = queue.poll();
			list.add(movie);
		}
		
		System.out.print("Top N movies by genre");
		System.out.print(list.toString());
		return list;
	}
}

class MovieComparator implements Comparator<Movie>{

	@Override
	public int compare(Movie o1, Movie o2) {
		
		if(o1.getAverageReviewScore() > o2.getAverageReviewScore()) return 1;
		
		return 0;
	}
	
}
