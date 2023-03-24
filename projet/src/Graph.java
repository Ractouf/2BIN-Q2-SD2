import java.io.*;
import java.util.*;

public class Graph {
    Map<String, Station> stations = new HashMap();
    protected Map<Integer, Ligne> lignes;
    protected Map<Station, Set<Troncon>> adjacences;

    public Graph(File l, File t) {
        String ligneActuelle;
        String[] tabLigneActuelle;

        try (BufferedReader bf = new BufferedReader(new FileReader(l)) ) {
            lignes = new HashMap<>();

            while ((ligneActuelle = bf.readLine()) != null) {
                tabLigneActuelle = ligneActuelle.split(",");
                Ligne ligne = new Ligne(
                        Integer.parseInt(tabLigneActuelle[0]),
                        tabLigneActuelle[1],
                        obtenirStationAvecNom(tabLigneActuelle[2]),
                        obtenirStationAvecNom(tabLigneActuelle[3]),
                        tabLigneActuelle[4],
                        Integer.parseInt(tabLigneActuelle[5])
                );
                lignes.put(Integer.parseInt(tabLigneActuelle[0]), ligne);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader bf = new BufferedReader(new FileReader(t)) ) {
            adjacences = new HashMap<>();

            while ((ligneActuelle = bf.readLine()) != null) {
                tabLigneActuelle = ligneActuelle.split(",");
                Troncon troncon = new Troncon(
                        obtenirStationAvecNom(tabLigneActuelle[1]),
                        obtenirStationAvecNom(tabLigneActuelle[2]),
                        Integer.parseInt(tabLigneActuelle[3]),
                        lignes.get(Integer.parseInt(tabLigneActuelle[0]))
                );
                if (adjacences.get(troncon.getDepart()) == null) {
                    adjacences.put(troncon.getDepart(), new HashSet<>());
                }
                adjacences.get(troncon.getDepart()).add(troncon);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void calculerCheminMinimisantNombreTroncons(String depart, String arrivee) {
        Set<Station> visite = new HashSet<>();
        Deque<Station> queue = new ArrayDeque<>();
        HashMap<Station, Troncon> chemin = new HashMap<>();

        Station stationDepart = obtenirStationAvecNom(depart);
        Station stationArrivee = obtenirStationAvecNom(arrivee);

        queue.add(stationDepart);
        visite.add(stationDepart);

        boolean trouve = false;
        while(!trouve) {
            Station stationActuelle = queue.removeFirst();

            for (Troncon troncon : adjacences.get(stationActuelle)) {
                Station arriveeTroncon = troncon.getArrivee();

                if (!visite.contains(arriveeTroncon)) {
                    visite.add(arriveeTroncon);

                    queue.add(arriveeTroncon);
                    chemin.put(arriveeTroncon, troncon);
                }

                if (arriveeTroncon.equals(stationArrivee)) {
                    trouve = true;
                }
            }
        }
        afficherCheminFinal(chemin, stationDepart, stationArrivee);
    }

    public void calculerCheminMinimisantTempsTransport(String depart, String arrivee) {
        TreeSet<Station> provisoires = new TreeSet<>(Comparator.comparingInt(Station::getTempsEtiquetteProvisoire).thenComparing(Station::getNom));
        Set<Station> definitifs = new HashSet<>();
        HashMap<Station, Troncon> chemin = new HashMap<>();

        Station stationDepart = obtenirStationAvecNom(depart);
        Station stationArrivee = obtenirStationAvecNom(arrivee);

        stationDepart.setTempsEtiquetteProvisoire(0);
        provisoires.add(stationDepart);

        while(!provisoires.isEmpty()) {
            Station stationActuelle = provisoires.pollFirst();
            definitifs.add(stationActuelle);

            if (stationActuelle.equals(stationArrivee))
                break;

            for (Troncon troncon : adjacences.get(stationActuelle)) {
                Station arriveeTroncon = troncon.getArrivee();

                if (!definitifs.contains(arriveeTroncon)) {
                    int tempsProvisoire = stationActuelle.getTempsEtiquetteProvisoire() + troncon.getDuree();

                    if (provisoires.contains(arriveeTroncon)) {
                        if (arriveeTroncon.getTempsEtiquetteProvisoire() > tempsProvisoire) {
                            provisoires.remove(arriveeTroncon);
                            arriveeTroncon.setTempsEtiquetteProvisoire(tempsProvisoire);
                            provisoires.add(arriveeTroncon);
                            chemin.put(arriveeTroncon, troncon);
                        }
                    } else {
                        arriveeTroncon.setTempsEtiquetteProvisoire(tempsProvisoire);
                        provisoires.add(arriveeTroncon);
                        chemin.put(arriveeTroncon, troncon);
                    }
                }
            }
        }
        afficherCheminFinal(chemin, stationDepart, stationArrivee);
    }

    private Station obtenirStationAvecNom(String stationName) {
        if (stations.get(stationName) == null) {
            stations.put(stationName, new Station(stationName));
        }
        return stations.get(stationName);
    }

    private void afficherCheminFinal(HashMap<Station, Troncon> chemin, Station depart, Station arrivee) {
        ArrayDeque<Deplacement> cheminFinal = new ArrayDeque<>();
        Troncon tronconDepart = chemin.get(arrivee);
        cheminFinal.addFirst(new Deplacement(tronconDepart.getLigne(), tronconDepart.getDepart(), tronconDepart.getArrivee(), tronconDepart.getDuree()));

        Ligne ligneActuelle = tronconDepart.getLigne();
        while (!cheminFinal.getFirst().getDepart().equals(depart)) {
            Troncon tronconActuel = chemin.get(cheminFinal.getFirst().getDepart());

            if (!ligneActuelle.equals(tronconActuel.getLigne())) {
                cheminFinal.addFirst(new Deplacement(tronconActuel.getLigne(), tronconActuel.getDepart(), tronconActuel.getArrivee(), tronconActuel.getDuree()));
                ligneActuelle = tronconActuel.getLigne();
            } else {
                Deplacement deplacement = cheminFinal.getFirst();
                deplacement.setDepart(tronconActuel.getDepart());
                deplacement.setDuree(deplacement.getDuree() + tronconActuel.getDuree());
                deplacement.incrementNbTroncons();
            }
        }

        int nbTroncons = 0;
        int dureeTransport = 0;
        int dureeTotale = 0;
        for (Deplacement deplacement : cheminFinal) {
            System.out.println(deplacement);

            nbTroncons += deplacement.getNbTroncons();
            dureeTransport += deplacement.getDuree();
            dureeTotale += deplacement.getLigne().getTempsMoyen() + dureeTransport;
        }
        System.out.println("nbTroncons=" + nbTroncons);
        System.out.println("dureeTransport=" + dureeTransport + " dureeTotale=" + dureeTotale);
    }
}
