public class Troncon {
    private final String depart;
    private final String arrivee;
    private final int duree;
    private final Ligne ligne;

    public Troncon(String depart, String arrivee, int duree, Ligne ligne) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.duree = duree;
        this.ligne = ligne;
    }

    public String getDepart() {
        return depart;
    }
    public String getArrivee() {
        return arrivee;
    }
    public int getDuree() {
        return duree;
    }
    public Ligne getLigne() {
        return ligne;
    }

    @Override
    public String toString() {
        return "Troncon [depart=" + getDepart() + ", arrivee=" + getArrivee() + ", durée=" + getDuree() + ", ligne=" + getLigne() + "]";
    }
}
