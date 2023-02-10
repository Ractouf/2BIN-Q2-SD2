package ex4;

import java.util.HashMap;
import java.util.Map;

public class ChoixOptions {
	
	// associe le nom d'une option avec son objet Option correspondant
	private Map<String, Option> options;
	// ajouter ici les autres attributs
	
	
	//constructeur prenant un entier et une suite de string en paramètres
	//ces string représentent les noms des différentes options possibles
	public ChoixOptions(int nbEtudiantsParOption, String... nomsOption) {
		this.options = new HashMap<String, Option>();
		if (nomsOption.length < 3)
			throw new IllegalArgumentException();
		for (int i = 0; i < nomsOption.length; i++) {
			String nomOption = nomsOption[i];
			options.put(nomOption, new Option(nomOption, nbEtudiantsParOption));
		}
		// initialiser les nouveaux attributs
	}

	// cette méthode encode les préférences des étudiants
	// il ne faut pas vérifier que ces choix soient valides
	public void ajouterPreferences(Etudiant etu, String choix1, String choix2,
			String choix3) {
	}

	// cette méthode est appelée après que les étudiants aient donné leurs préférences
	// cette méthode attribue les options aux étudiants en favorisant les étudiants 
	// ayant les meilleures moyennes si il n'y a plus de place disponible dans certaines 
	// options. Pour les étudiants faibles, si les deux premières options sont pleines, 
	// il faut recourir au troisième choix.
	// Cette méthode doit faire appel à la méthode inscrireEtudiant de la classe Option.
	public void attribuerOptions() {
	}
	
	public String toString(){
		String s="";
		for (Option o:options.values()){
			s=s+o+"\n"+"-----------------"+"\n";
		}
		return s;
	}
}
