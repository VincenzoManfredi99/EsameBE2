package vincenzomanfredi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Collezione {

    public List<Gioco> listaGiochi;

    public Collezione() {

        this.listaGiochi = new ArrayList<>();
    }


    public void aggiungiGioco(Gioco nuovoGioco) throws Exception {
        boolean doppione = listaGiochi.stream().anyMatch(g -> g.getId() == nuovoGioco.getId());
        if (doppione) {
            throw new Exception("Errore, questo id è già registrato");
        } else listaGiochi.add(nuovoGioco);
    }

    public String ricercaPerId(int idCercato) throws Exception {

        String nomeGioco = listaGiochi.stream().filter(g -> g.getId() == idCercato).map(Gioco::getTitolo).collect(Collectors.joining());

        if (nomeGioco.isEmpty()) {
            throw new Exception(" l'id che stai cercando non esiste");
        }
        return nomeGioco;
    }

    public List<String> ricercaPerPrezzo(double prezzoMassimo) throws Exception {
        List<String> giochiInferiori = listaGiochi.stream().filter(g -> g.getPrezzo() < prezzoMassimo).map(Gioco::getTitolo).toList();
        if (giochiInferiori.isEmpty()) {
            throw new Exception(" non esistono giochi con un prezzo inferiore a " + prezzoMassimo + " €");
        }
        return giochiInferiori;
    }

    public List<String> ricercaPerNumGiocatori(int ngiocatori) throws Exception {
        List<String> giocoPerNumGiocatori =
                listaGiochi.stream().filter(g -> g instanceof BoardGame).map(g -> (BoardGame) g) //Conversione in BoardGame per accedere a getNgiocatori
                        .filter(bg -> bg.getNgiocatori() == ngiocatori).map(BoardGame::getTitolo).toList();

        if (giocoPerNumGiocatori.isEmpty()) {
            throw new Exception(" non esistono giochi con questo numero di giocatori");
        }
        return giocoPerNumGiocatori;
    }

    public void rimozioneTramiteId(int idDaRimuovere) throws Exception {
        List<Gioco> listaGiochiDaCancellare = listaGiochi.stream().filter(g -> g.getId() == idDaRimuovere).toList();
        if (listaGiochiDaCancellare.isEmpty()) {
            throw new Exception("l'id inserito non è presente nella tua collezione");
        }
        Gioco giocoDaEliminare = listaGiochiDaCancellare.getFirst();
        listaGiochi.remove(giocoDaEliminare);
    }

    @Override
    public String toString() {
        return listaGiochi.stream().map(Gioco::toString).collect(Collectors.joining("\n"));
    }


}
