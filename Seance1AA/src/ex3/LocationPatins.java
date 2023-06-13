package ex3;

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static java.time.temporal.ChronoUnit.MILLIS;

public class LocationPatins {
	private HashMap<Integer, ArrayDeque<Integer>> pointures;
	private HashMap<Integer, LocalTime> casiersOccupes;
	private int[] casiersBase;

	public LocationPatins(int[] casiers) {
		pointures = new HashMap<>();
		casiersOccupes = new HashMap<>();
		casiersBase = casiers;

		for (int i = 0; i < casiers.length; i++) {
			if (casiers[i] < 33 || casiers[i] > 48)
				continue;
			if (!pointures.containsKey(casiers[i]))
				pointures.put(casiers[i], new ArrayDeque<>());

			pointures.get(casiers[i]).add(i);
		}
	}

	// date1 < date2
	private static double prix(LocalTime date1, LocalTime date2) {
		// 1 euro par milliseconde (c'est assez cher en effet)
		return MILLIS.between(date1, date2) ;
	}

	public int attribuerCasierAvecPatins(int pointure) {
		if (pointure < 33 || pointure > 48)
			throw new IllegalArgumentException();

		int casier = 0;

		try {
			casier = pointures.get(pointure).removeFirst();
		} catch (NoSuchElementException e) {
			return -1;
		}

		LocalTime l = LocalTime.now();

		casiersOccupes.put(casier, l);
		return casier;
	}

	public double libererCasier(int numeroCasier) {
		if (!casiersOccupes.containsKey(numeroCasier))
			return -1;

		pointures.get(casiersBase[numeroCasier]).add(numeroCasier);
		double prix = prix(casiersOccupes.get(numeroCasier), LocalTime.now());
		casiersOccupes.remove(numeroCasier);

		return prix;
	}
}
