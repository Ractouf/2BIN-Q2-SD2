package ex2;

import java.util.HashMap;
import java.util.HashSet;

public class ControleDAcces {
	HashSet<Employe> employesPresents;
	HashMap<Badge, Employe> employes;

	public ControleDAcces() {
		employesPresents = new HashSet<>();
		employes = new HashMap<>();
	}
	
	// associe le badge � un employ�
	public void donnerBadge (Badge b, Employe e) {
		if (employes.containsValue(e))
			throw new IllegalArgumentException("Employé possède déjà un badge");

		employes.put(b, e);
	}
	
	// met � jour les employ�s pr�sents dans le batiment
	public void entrerBatiment (Badge b) {
		employesPresents.add(employes.get(b));
	}

	// met � jour les employ�s pr�sents dans le batiment
	public void sortirBatiment (Badge b) {
		employesPresents.remove(employes.get(b));
	}
	
	// renvoie vrai si l'employ� est dans le batiment, faux sinon
	public boolean estDansBatiment (Employe e) {
		return employesPresents.contains(e);
	}

}
