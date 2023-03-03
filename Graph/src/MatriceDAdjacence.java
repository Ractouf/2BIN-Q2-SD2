import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MatriceDAdjacence extends Graph{
	
	private Map<Integer, Airport>  correspondanceIndiceAirport;
	private Map<Airport, Integer>  correspondanceAirportIndice;
	private Flight[][] matrice = new Flight[0][0];
	private int nbAirport = 0;

	public MatriceDAdjacence() {
		super();
		correspondanceAirportIndice = new HashMap<>();
		correspondanceIndiceAirport = new HashMap<>();
	}

	@Override
	// Complexité: O(1)
	protected void ajouterSommet(Airport a) {
		correspondanceAirportIndice.put(a, nbAirport);
		correspondanceIndiceAirport.put(nbAirport, a);
		nbAirport ++;
		matrice = new Flight[nbAirport][nbAirport];
	}

	@Override
	// Complexité: O(1)
	protected void ajouterArc(Flight f) {
		matrice[correspondanceAirportIndice.get(f.getSource())][correspondanceAirportIndice.get(f.getDestination())] = f;
	}

	@Override
	// Complexité: O(n)
	public Set<Flight> arcsSortants(Airport a) {
		Set<Flight> volsSortants = new HashSet<>();
		for (int i = 0; i < nbAirport; i++) {
			Flight flight = matrice[correspondanceAirportIndice.get(a)][i];

			if (flight != null)
				volsSortants.add(flight);
		}

		return volsSortants;
	}

	@Override
	// Complexité: O(1)
	public boolean sontAdjacents(Airport a1, Airport a2) {
		int indiceA1 = correspondanceAirportIndice.get(a1);
		int indiceA2 = correspondanceAirportIndice.get(a2);

		return matrice[indiceA1][indiceA2] != null || matrice[indiceA2][indiceA1] != null;
	}
	
	

}
