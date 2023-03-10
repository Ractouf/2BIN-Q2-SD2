import java.util.Objects;

public class Ligne {
    private final int id;
    private final String numero;
    private final String source;
    private final String destination;
    private final String type;
    private final int tempsMoyen;

    public Ligne(int id, String numero, String depart, String destintination, String typeTransport, int tempsMoyen) {
        this.id = id;
        this.numero = numero;
        this.source = depart;
        this.destination = destintination;
        this.type = typeTransport;
        this.tempsMoyen = tempsMoyen;
    }

    public int getId() {
        return id;
    }
    public String getNumero() {
        return numero;
    }
    public String getSource() {
        return source;
    }
    public String getDestination() {
        return destination;
    }
    public String getType() {
        return type;
    }
    public int getTempsMoyen() {
        return tempsMoyen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ligne ligne = (Ligne) o;
        return id == ligne.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ligne [id=" + getId() + ", nom=" + getNumero() + ", source=" + getSource() + ", destination=" + getDestination() + ", type=" + getType() + ", attente moyenne=" + getTempsMoyen() + "]";
    }
}
