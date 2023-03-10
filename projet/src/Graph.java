import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    protected HashMap<Integer, Ligne> lignes;

    protected Map<String, Set<Troncon>> adjacence;

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
                        tabCurrentLine[1],
                        tabCurrentLine[2],
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
    }

    public void calculerCheminMinimisantTempsTransport(String depart, String arrivee) {

    }
}
