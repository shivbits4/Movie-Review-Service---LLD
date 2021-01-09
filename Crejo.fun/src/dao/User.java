package dao;

import java.util.UUID;

import exceptions.InvalidDate;
import exceptions.InvalidReviewScore;
import exceptions.InvalidUser;
import services.Service;

public class User {
	
	String userId;
	String fname;
	String lname;
	Service reviewService;
	int numberOfReviews;
	UserStatus userStatus;
	
	public User(String fname, String lname) {
		
		this.userId = UUID.randomUUID().toString();
		this.fname = fname;
		this.lname = lname;
		this.userStatus = UserStatus.VIEWER;
		this.numberOfReviews = 0;
	}
	
	public void incrementReview() {
		this.numberOfReviews++;
	}
	
	public void upgradeUser(UserStatus userStatus) {
		
		this.userStatus = userStatus;
	}
	
	public boolean review(Movie movie, double rating) {
		try {
			return reviewService.review(this, movie, rating);
		} catch (InvalidReviewScore | InvalidDate | InvalidUser e) {
			System.out.println("Exception occured: "+e);
		}
		return false;
	}

	public String getUserId() {
		return userId;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public Service getReviewService() {
		return reviewService;
	}

	public int getNumberOfReviews() {
		return numberOfReviews;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}
	
	

}
