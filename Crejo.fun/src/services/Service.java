package services;

import dao.Movie;
import dao.User;
import exceptions.InvalidDate;
import exceptions.InvalidReviewScore;
import exceptions.InvalidUser;

public interface Service {

	public boolean review(User user, Movie movie, double rating) throws InvalidReviewScore, InvalidDate, InvalidUser;
}
