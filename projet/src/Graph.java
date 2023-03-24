import java.io.*;
import java.util.*;

public class Graph {
    protected HashMap<Integer, Ligne> lignes;

    protected Map<Station, Set<Troncon>> adjacence;

    public Graph(File l, File t) {
        Ligne ligne;
        Troncon troncon;
        String currentLine;
        String[] tabCurrentLine;

        try (BufferedReader bf = new BufferedReader(new FileReader(l)) ) {
            lignes = new HashMap<>();

            while ((currentLine = bf.readLine()) != null) {
                tabCurrentLine = currentLine.split(",");
                ligne = new Ligne(
                        Integer.parseInt(tabCurrentLine[0]),
                        tabCurrentLine[1],
                        new Station(tabCurrentLine[2]),
                        new Station(tabCurrentLine[3]),
                        tabCurrentLine[4],
                        Integer.parseInt(tabCurrentLine[5])
                );
                lignes.put(Integer.parseInt(tabCurrentLine[0]), ligne);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader bf = new BufferedReader(new FileReader(t)) ) {
            adjacence = new HashMap<>();

            while ((currentLine = bf.readLine()) != null) {
                tabCurrentLine = currentLine.split(",");
                troncon = new Troncon(
                        new Station(tabCurrentLine[1]),
                        new Station(tabCurrentLine[2]),
                        Integer.parseInt(tabCurrentLine[3]),
                        lignes.get(Integer.parseInt(tabCurrentLine[0]))
                );
                if (adjacence.get(troncon.getDepart()) == null) {
                    adjacence.put(troncon.getDepart(), new HashSet<>());
                }
                adjacence.get(troncon.getDepart()).add(troncon);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void calculerCheminMinimisantNombreTroncons(String depart, String arrivee) {
        Station stationDepart = new Station(depart);
        Station stationArrivee = new Station(arrivee);

        Set<Station> visite = new HashSet<>();
        ArrayDeque<Station> queue = new ArrayDeque<>();
        HashMap<Station, Troncon> chemin = new HashMap<>();

        Station currentStation = stationDepart;

        queue.add(stationDepart);
        visite.add(stationDepart);

        boolean found = false;
        while(!found) {
            currentStation = queue.removeFirst();

            Set<Troncon> troncons = adjacence.get(currentStation);

            for (Troncon troncon : troncons) {
                Station departTroncon = troncon.getDepart();
                Station arriveeTroncon = troncon.getArrivee();

                if (!visite.contains(arriveeTroncon)) {
                    visite.add(arriveeTroncon);

                    queue.add(arriveeTroncon);
                    chemin.put(arriveeTroncon, troncon);
                }

                if (arriveeTroncon.equals(stationArrivee)) {
                    found = true;
                }
            }
        }

        int dureeTransport = 0;
        int dureeTotale = 0;
        Ligne currentLigne;

        ArrayDeque<Troncon> cheminFinal = new ArrayDeque<>();
        cheminFinal.addFirst(chemin.get(stationArrivee));
        dureeTransport += cheminFinal.getFirst().getDuree();

        currentLigne = cheminFinal.getFirst().getLigne();
        dureeTotale += currentLigne.getTempsMoyen();

        while (!cheminFinal.getFirst().getDepart().equals(stationDepart)) {
            cheminFinal.addFirst(chemin.get(cheminFinal.getFirst().getDepart()));

            if (!cheminFinal.getFirst().getLigne().equals(currentLigne)) {
                currentLigne = cheminFinal.getFirst().getLigne();
                dureeTotale += currentLigne.getTempsMoyen();
            }

            dureeTransport += cheminFinal.getFirst().getDuree();
        }

        dureeTotale += dureeTransport;

        for (Troncon troncon : cheminFinal) {
            System.out.println(troncon);
        }
        System.out.println("nbTroncons=" + cheminFinal.size());
        System.out.println("dureeTransport=" + dureeTransport + " dureeTotale=" + dureeTotale);
    }

    public void calculerCheminMinimisantTempsTransport(String depart, String arrivee) {
        TreeSet<Station> provisoires = new TreeSet<>(Comparator.comparingInt(Station::getTempsEtiquetteProvisoire).thenComparing(Station::getNom));
        Set<Station> definitif = new HashSet<>();
        HashMap<Station, Troncon> chemin = new HashMap<>();

        Station stationDepart = new Station(depart);
        Station stationFinale = new Station(arrivee);

        stationDepart.setTempsEtiquetteProvisoire(0);
        provisoires.add(stationDepart);
        Station stationCourrante = stationDepart;

        while(!provisoires.isEmpty()) {
            stationCourrante = provisoires.pollFirst();
            definitif.add(stationCourrante);

            if (stationCourrante.equals(stationFinale))
                break;

            for (Troncon troncon : adjacence.get(stationCourrante)) {
                if (!definitif.contains(troncon.getArrivee())) {
                    int tempsProvisoire = stationCourrante.getTempsEtiquetteProvisoire() + troncon.getDuree();
                    if(provisoires.contains(troncon.getArrivee())) {
                        if(troncon.getArrivee().getTempsEtiquetteProvisoire() > tempsProvisoire) {
                            provisoires.remove(troncon.getArrivee());
                            troncon.getArrivee().setTempsEtiquetteProvisoire(tempsProvisoire);
                            provisoires.add(troncon.getArrivee());
                            chemin.put(troncon.getArrivee(), troncon);
                        }
                    } else {
                        troncon.getArrivee().setTempsEtiquetteProvisoire(tempsProvisoire);
                        provisoires.add(troncon.getArrivee());
                        chemin.put(troncon.getArrivee(), troncon);
                    }
                }
            }

        }

        int dureeTransport = 0;
        int dureeTotale = 0;
        Ligne currentLigne;

        ArrayDeque<Troncon> cheminFinal = new ArrayDeque<>();
        cheminFinal.addFirst(chemin.get(stationFinale));
        dureeTransport += cheminFinal.getFirst().getDuree();

        currentLigne = cheminFinal.getFirst().getLigne();
        dureeTotale += currentLigne.getTempsMoyen();

        while (!cheminFinal.getFirst().getDepart().equals(stationDepart)) {
            cheminFinal.addFirst(chemin.get(cheminFinal.getFirst().getDepart()));

            if (!cheminFinal.getFirst().getLigne().equals(currentLigne)) {
                currentLigne = cheminFinal.getFirst().getLigne();
                dureeTotale += currentLigne.getTempsMoyen();
            }

            dureeTransport += cheminFinal.getFirst().getDuree();
        }

        dureeTotale += dureeTransport;

        for (Troncon troncon : cheminFinal) {
            System.out.println(troncon);
        }
        System.out.println("nbTroncons=" + cheminFinal.size());
        System.out.println("dureeTransport=" + dureeTransport + " dureeTotale=" + dureeTotale);
    }
}
