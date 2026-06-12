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

    @Override
    public String toString() {
        return listaGiochi.stream().map(Gioco::toString).collect(Collectors.joining("\n"));
    }


}
