package ex5;

public class Doodle {
	private PlageHoraire[] plages;
	// a compléter

	public Doodle(PlageHoraire... plages) {
		this.plages = plages;
		// a compléter
	}

	// ajoute les disponibilités d'un participant sous forme d'un tableau de booleen.
	// les indices du tableau correspondent aux indices du tableau plages
	// true à l'indice i veut dire que le participant est disponible pour la plage à l'indice i du tableau plages
	// false à l'indice i veut dire que le participant n'est pas disponible pour la plage à l'indice i du tableau plages
	public void ajouterDisponibilites(String participant,
			boolean[] disponibilites) {
		if (disponibilites.length != plages.length)
			throw new IllegalArgumentException();
		//a compléter
	}
	
	// Hypothèse: la PlageHoraire plage en paramètre fait bien partie du tableau plages
	// renvoie vrai si le participant est disponible pour cette plage horaire
	// renvoie faux si le participant n'est pas disponible ou s'il n'a pas rempli le
	// sondage doodle
	public boolean estDisponible(String participant, PlageHoraire plage) {
		return false;
	}

	// renvoie une des plages horaires qui a le maximum de participants prévus
	// cette méthode est appelée après que les participants aient rempli leurs disponibilités
	public PlageHoraire trouverPlageQuiConvientLeMieux() {
		return null;
	}

}
