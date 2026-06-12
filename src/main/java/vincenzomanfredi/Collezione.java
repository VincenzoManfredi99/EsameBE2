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

    @Override
    public String toString() {
        return listaGiochi.stream().map(Gioco::toString).collect(Collectors.joining("\n"));
    }


}
