package vincenzomanfredi;

public class Gioco {

    int id;
    String titolo;
    int annoUscita;
    double prezzo;

    public Gioco(int id, String titolo, int annoUscita, double prezzo) {
        this.id = id;
        this.titolo = titolo;
        this.annoUscita = annoUscita;
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return id + " - " + titolo + " - " + annoUscita + " - " + prezzo + "€ ";
    }

    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public double getPrezzo() {
        return prezzo;
    }
}
