package vincenzomanfredi;

import java.util.List;

public class Videogiochi extends Gioco {
    List<String> piattaforma;
    int durata;
    List<Genere> genere;

    public Videogiochi(int id, String titolo, int annoUscita, double prezzo, List<String> piattaforma, int durata, List<Genere> genere) {
        super(id, titolo, annoUscita, prezzo);
        this.piattaforma = piattaforma;
        this.durata = durata;
        this.genere = genere;
    }

    @Override
    public String toString() {
        return super.toString() + piattaforma + " - " + durata + "h " + " - " + genere;
    }
}
