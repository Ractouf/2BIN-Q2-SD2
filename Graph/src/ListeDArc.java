import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ListeDArc extends Graph{
	
	private ArrayList<Flight> flights;

	public ListeDArc() {
		super();
		flights = new ArrayList<Flight>();
	}

	@Override
	// Complexit�: 1
	protected void ajouterSommet(Airport a) {

	}

	@Override
	// Complexit�: O(1)
	protected void ajouterArc(Flight f) {
		flights.add(f);
	}

	@Override
	// Complexit�: O(n)
	public Set<Flight> arcsSortants(Airport a) {
		Set<Flight> volSortants = new HashSet<>();
		for (Flight f : flights) {
			if (f.getSource().equals(a))
				volSortants.add(f);
		}
		return volSortants;
	}

	@Override
	// Complexit�: O(n)
	public boolean sontAdjacents(Airport a1, Airport a2) {
		for (Flight f : flights) {
			if ((f.getSource().equals(a1) && f.getDestination().equals(a2))
					|| (f.getDestination().equals(a1) && f.getSource().equals(a2)))
				return true;
		}
		return false;
	}
}
