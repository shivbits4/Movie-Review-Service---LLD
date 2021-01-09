package services;

import java.util.HashMap;
import java.util.List;

import dao.Review;
import dao.UserStatus;

public interface ScoreCalculationStrategy {

	public double calculateScore(HashMap<UserStatus, List<Review>> reviews);
}
