package vincenzomanfredi;

public class BoardGame extends Gioco {
    int nGiocatori;
    int durataPartita;

    public BoardGame(int id, String titolo, int annoUscita, double prezzo, int nGiocatori, int durataPartita) {
        super(id, titolo, annoUscita, prezzo);
        this.nGiocatori = nGiocatori;
        this.durataPartita = durataPartita;
    }

    @Override
    public String toString() {
        return super.toString() + nGiocatori + " GIocatori" + " - " + durataPartita + " min per partita";
    }
}
