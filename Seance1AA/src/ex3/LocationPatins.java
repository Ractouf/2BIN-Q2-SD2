package ex3;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static java.time.temporal.ChronoUnit.MILLIS;

public class LocationPatins {
	HashMap<Integer, Set<Integer>> pointures;
	HashMap<Integer, LocalTime> casiersOccupes;
	public LocationPatins(int[] casiers) {
		for (int casier : casiers) {
			if (casier < 33 || casier > 48)
				continue;
			if (pointures.containsKey(casier))
				continue;

			pointures.put(casier, new HashSet<>());
		}

		casiersOccupes = new HashMap<>();
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
		
		
		return 0;

	}

	public double libererCasier(int numeroCasier) {
		return 0;
	}

}
