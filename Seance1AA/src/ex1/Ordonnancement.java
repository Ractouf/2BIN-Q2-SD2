package ex1;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Ordonnancement {
	public static final int NIVEAU_PRIORITE_MAX=5;
	private HashMap<Integer, ArrayDeque<Tache>> map;

	public Ordonnancement() {
		map = new HashMap<>();

		for (int i = 0; i < NIVEAU_PRIORITE_MAX; i++) {
			map.put(i, new ArrayDeque<>());
		}
	}
	public void ajouterTache (String descriptif, int priorite) {
		if (priorite < 0 || priorite > 5)
			throw new IllegalArgumentException("Priorité inexistante");

		map.get(priorite - 1).add(new Tache(descriptif, priorite));
	}
	/***
	 * @return la tache prioritaire || null si plus de tache presente
	 */
	public Tache attribuerTache() {
		for (int i = NIVEAU_PRIORITE_MAX - 1; i >= 0; i--) {
			if (map.get(i).isEmpty())
				continue;

			return map.get(i).remove();
		}
		return null;
	}
}
