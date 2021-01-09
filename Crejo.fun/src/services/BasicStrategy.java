package services;

import java.util.HashMap;
import java.util.List;

import dao.Review;
import dao.UserStatus;

public class BasicStrategy implements ScoreCalculationStrategy{

	@Override
	public double calculateScore(HashMap<UserStatus, List<Review>> reviews) {
		// TODO Auto-generated method stub
		return 0;
	}



}
