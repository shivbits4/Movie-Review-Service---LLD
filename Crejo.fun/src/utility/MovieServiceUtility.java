package utility;

import java.util.Date;
import java.util.HashMap;

import dao.Review;
import dao.UserStatus;

public class MovieServiceUtility {

	public static UserStatus upgradeUser(int numberOfReviews) {
		
		if(numberOfReviews>=3) {
			
			System.out.println("User has been upgraded to CRITIC");
			return UserStatus.CRITIC;
		} 
		
		return UserStatus.VIEWER;
	}

	public static boolean checkValidUser(String string, HashMap<String, Review> hashMap) {
		
		if(hashMap.containsKey(string)) {
			return false;
		}
		
		return true;
	}

	public static boolean checkValidDate(Date releaseDate, Date todayDate) {
		
		return releaseDate.before(todayDate) ? true : false   ;
	}
	

	@SuppressWarnings("deprecation")
	public static int getYearFromDate(Date releaseDate) {
		return releaseDate.getYear();
	}
	

}
