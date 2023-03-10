import java.util.Objects;

public class Ligne {
    private final int id;
    private final String numero;
    private final Station source;
    private final Station destination;
    private final String type;
    private final int tempsMoyen;

    public Ligne(int id, String numero, Station source, Station destination, String type, int tempsMoyen) {
        this.id = id;
        this.numero = numero;
        this.source = source;
        this.destination = destination;
        this.type = type;
        this.tempsMoyen = tempsMoyen;
    }

    public int getId() {
        return id;
    }
    public String getNumero() {
        return numero;
    }
    public Station getSource() {
        return source;
    }
    public Station getDestination() {
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
