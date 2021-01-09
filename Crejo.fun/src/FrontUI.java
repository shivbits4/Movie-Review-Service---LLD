import java.sql.Date;

import dao.Genre;
import dao.Movie;
import dao.User;
import exceptions.InvalidDate;
import exceptions.InvalidGenre;
import exceptions.InvalidReviewScore;
import exceptions.InvalidUser;
import services.MovieReviewService;

public class FrontUI {

	public static void main(String[] args) {
		
		MovieReviewService movieReviewService = new MovieReviewService();
		//onBoardMovies(movieReviewService);
		
		        // Movie 1
				Date d1 = new Date(2003, 11, 11);
				Movie m1 = new Movie("Don", d1, Genre.ACTION);
				movieReviewService.addMovie(m1);
				
		        
				// Movie 2
				Date d2 = new Date(2004, 11, 11);
				Movie m2 = new Movie("Tiger", d1, Genre.ACTION);
				movieReviewService.addMovie(m2);

				// Movie 3
				Date d3 = new Date(2005, 11, 11);
				Movie m3 = new Movie("m3", d3, Genre.DRAMA);
				movieReviewService.addMovie(m3);

				// Movie 4
				Date d4 = new Date(2003, 4, 11);
				Movie m4 = new Movie("m4", d4, Genre.HORROR);
				movieReviewService.addMovie(m4);
				
				// Movie 5
				Date d5 = new Date(2004, 1, 11);
				Movie m5 = new Movie("m5", d1, Genre.ROMANTIC);
				movieReviewService.addMovie(m5);

				// Movie 6
				Date d6 = new Date(2005, 11, 1);
				Movie m6 = new Movie("m6", d6, Genre.MYSTERY);
				movieReviewService.addMovie(m6);
				
				// Movie 7
				Date d7 = new Date(2003, 10, 11);
				Movie m7 = new Movie("m7", d7, Genre.ROMANTIC);
				movieReviewService.addMovie(m7);

				// Movie 8
				Date d8 = new Date(2003, 3, 11);
				Movie m8 = new Movie("m8", d1, Genre.ACTION);
				movieReviewService.addMovie(m8);

				// Movie 9
				Date d9 = new Date(2003, 5, 11);
				Movie m9 = new Movie("m9", d5, Genre.DRAMA);
				movieReviewService.addMovie(m9);

				// Movie 10
				Date d10 = new Date(2003, 11, 11);
				Movie m10 = new Movie("m10", d10, Genre.ACTION);
				movieReviewService.addMovie(m10);
		
		// Adding users
		User u1 = new User("Shiv", "D");
		User u2 = new User("Ash", "S");
		User u3 = new User("Rkd", "G");
		movieReviewService.addUser(u1);
		movieReviewService.addUser(u2);
		movieReviewService.addUser(u3);
		
		try {
			movieReviewService.review(u1, m1, 2.3);
			
			movieReviewService.review(u2, m2, 7.8);
			movieReviewService.review(u3, m3, 7);
			movieReviewService.review(u3, m4, 3);
			movieReviewService.review(u3, m5, 5.6);
		} catch (InvalidReviewScore | InvalidDate | InvalidUser e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		try {
			movieReviewService.review(new User("S", "D"), m1, 2.5);
		} catch (InvalidReviewScore | InvalidDate | InvalidUser e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			movieReviewService.review(u3, m5, 5.8);
		} catch (InvalidReviewScore | InvalidDate | InvalidUser e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			movieReviewService.getAverageReviewScoreForGenre(Genre.ACTION);
			movieReviewService.getAverageReviewScoreForYear("2003");
			movieReviewService.getAverageReviewScoreForGenre(Genre.THRILLER);
		} catch (InvalidGenre | InvalidDate e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void onBoardMovies(MovieReviewService movieReviewService) {
		

	}

}
