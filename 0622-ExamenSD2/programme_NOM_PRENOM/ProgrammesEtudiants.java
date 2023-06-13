import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class ProgrammesEtudiants {
	private Set<Etudiant> etudiants = new HashSet<Etudiant>();
	private HashMap<UniteEnseignement, HashSet<Etudiant>> etudiantsValides;
	private TreeSet<Etudiant> treeSet;
	
	public ProgrammesEtudiants(Etudiant... etudiants) {
		for (Etudiant etudiant : etudiants) {
			this.etudiants.add(etudiant);
		}

		etudiantsValides = new HashMap<>();
		treeSet = new TreeSet<>(Comparator.comparingInt(Etudiant::getNbEctsValides).reversed());
	}

	public void valider(Etudiant e, UniteEnseignement ue) {
		if (!etudiantsValides.containsKey(ue)) {
			etudiantsValides.put(ue, new HashSet<>());
		}

		if (etudiantsValides.get(ue).contains(e)) {
			throw new RuntimeException("UE déjà validée");
		}

		int nbrEcts = e.getNbEctsValides();
		e.setNbEctsValides(nbrEcts + ue.getNbEcts());

		etudiantsValides.get(ue).add(e);
	}

	public void afficherEtudiantsTriesParEcts() {
		for (Etudiant e : etudiants) {
			treeSet.add(e);
		}

		for (Etudiant e : treeSet) {
			System.out.println(e.getNom() + " " + e.getPrenom() + " " + e.getNbEctsValides() + " ects");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Etudiant e1 = new Etudiant(123456, "Durant", "Pol");
		Etudiant e2 = new Etudiant(123453, "Delcourt", "Alain");
		Etudiant e3 = new Etudiant(123452, "Michel", "Jean");
		ProgrammesEtudiants p = new ProgrammesEtudiants(e1, e2, e3);
		UniteEnseignement sd2 = new UniteEnseignement("SD2", 4);
		UniteEnseignement bd2 = new UniteEnseignement("BD2", 6);
		UniteEnseignement mobile = new UniteEnseignement("Mobile", 4);
		p.valider(e1, sd2);
		p.valider(e1, mobile);
		p.valider(e2, bd2);
		p.valider(e2, mobile);
		p.afficherEtudiantsTriesParEcts();
		Thread.sleep(50); //cette ligne est uniquement presente pour l'affichage
		p.valider(e1, mobile);
	}
}
