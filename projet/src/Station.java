import java.util.Objects;

public class Station {
    private final String nom;
    private int tempsEtiquetteProvisoire;

    public Station(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public int getTempsEtiquetteProvisoire() {
        return tempsEtiquetteProvisoire;
    }

    public void setTempsEtiquetteProvisoire(int tempsEtiquetteProvisoire) {
        this.tempsEtiquetteProvisoire = tempsEtiquetteProvisoire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(nom, station.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return getNom();
    }
}
