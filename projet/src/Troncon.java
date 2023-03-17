import java.util.Objects;

public class Troncon {
    private final Station depart;
    private final Station arrivee;
    private final int duree;
    private final Ligne ligne;

    public Troncon(Station depart, Station arrivee, int duree, Ligne ligne) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.duree = duree;
        this.ligne = ligne;
    }

    public Station getDepart() {
        return depart;
    }
    public Station getArrivee() {
        return arrivee;
    }
    public int getDuree() {
        return duree;
    }
    public Ligne getLigne() {
        return ligne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Troncon troncon = (Troncon) o;
        return Objects.equals(depart, troncon.depart) && Objects.equals(arrivee, troncon.arrivee) && Objects.equals(ligne, troncon.ligne);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depart, arrivee, ligne);
    }

    @Override
    public String toString() {
        return "Troncon [depart=" + getDepart() + ", arrivee=" + getArrivee() + ", durée=" + getDuree() + ", ligne=" + getLigne() + "]";
    }
}
