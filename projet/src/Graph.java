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
        Set<Station> visited = new HashSet<>();
        ArrayDeque<Station> queue = new ArrayDeque<>();
        HashMap<Station, Station> chemin = new HashMap<>();

        Station stationDepart = new Station(depart);
        Station stationArrivee = new Station(arrivee);

        Station currentStation = stationDepart;

        queue.add(stationDepart);
        visited.add(stationDepart);

        boolean found = false;
        while(!found) {
            currentStation = queue.removeFirst();

            Set<Troncon> troncons = adjacence.get(currentStation);

            for (Troncon troncon : troncons) {
                Station departTroncon = troncon.getDepart();
                Station arriveeTroncon = troncon.getArrivee();

                if (!visited.contains(arriveeTroncon)) {
                    visited.add(arriveeTroncon);

                    queue.add(arriveeTroncon);
                    chemin.put(arriveeTroncon, departTroncon);
                }

                if (arriveeTroncon.equals(stationArrivee)) {
                    found = true;
                }
            }
        }

        ArrayDeque<Station> cheminFinal = new ArrayDeque<>();
        cheminFinal.addFirst(stationArrivee);

        while (!cheminFinal.getFirst().equals(stationDepart)) {
            cheminFinal.addFirst(chemin.get(cheminFinal.getFirst()));
        }

        for (Station station : cheminFinal) {
            System.out.println(station.getNom());
        }
    }

    public void calculerCheminMinimisantTempsTransport(String depart, String arrivee) {

    }
}
