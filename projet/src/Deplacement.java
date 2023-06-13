public class Deplacement {
  Ligne ligne;
  Station depart;
  Station arrivee;
  int duree;
  int nbTroncons;

  public Deplacement(Ligne ligne, Station depart, Station arrivee, int duree) {
    this.ligne = ligne;
    this.depart = depart;
    this.arrivee = arrivee;
    this.duree = duree;

    this.nbTroncons = 1;
  }

  public Ligne getLigne() {
    return ligne;
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
  public int getNbTroncons() {
    return nbTroncons;
  }

  public void setDepart(Station depart) {
    this.depart = depart;
  }
  public void setDuree(int duree) {
    this.duree = duree;
  }
  public void incrementNbTroncons() {
    this.nbTroncons ++;
  }

  @Override
  public String toString() {
    return "Deplacement [ligne=" + ligne.getNumero() + ", depart=" + depart + ", arrivee=" + arrivee + ", attenteMoyenne=" + ligne.getTempsMoyen() + ", duree=" + duree + ", nbTroncon=" + nbTroncons + ", type=" + ligne.getType() + ", direction=" + ligne.getDestination() + "]";
  }
}
