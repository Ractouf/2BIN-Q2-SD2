package ex3;

import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MILLIS;

public class LocationPatins {
	
	public LocationPatins(int[] casiers) {
	
	}

	// date1 < date2
	private static double prix(LocalTime date1, LocalTime date2) {
		// 1 euro par milliseconde (c'est assez cher en effet)
		return MILLIS.between(date1, date2) ;
	}

	public int attribuerCasierAvecPatins(int pointure) {
		if (pointure < 33 || pointure > 48)
			throw new IllegalArgumentException();
		LocalTime l = LocalTime.now();
		
		
		//a compléter

	}

	public double libererCasier(int numeroCasier) {
		//a completer
	}

}
