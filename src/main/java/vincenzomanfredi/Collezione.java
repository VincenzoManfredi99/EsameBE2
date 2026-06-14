package vincenzomanfredi;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Collezione {
    public List<Gioco> listaGiochi;
    Scanner tastiera = new Scanner(System.in);


    public Collezione() {

        this.listaGiochi = new ArrayList<>();
    }


    public String inputTitolo() {
        while (true) {
            String titolo = tastiera.nextLine().trim();
            if (titolo.isEmpty()) {
                System.out.println("Errore, inserisci un titolo valido");
                continue; // rimane dentro il ciclo ricominciandolo
            }
            return titolo;
        }
    }

    public int inputId() {
        while (true) {
            int idDaCercare = Integer.parseInt(tastiera.nextLine().trim());
            boolean esiste = listaGiochi.stream().anyMatch(g -> g.getId() == idDaCercare);
            if (esiste) {
                System.out.println("Errore, l'id che hai inserito è già occupato");
                continue;
            }
            return idDaCercare;
        }
    }

    public int inputPrezzo() {
        while (true) {
            int prezzo = Integer.parseInt(tastiera.nextLine().trim());
            if (prezzo <= 0) {
                System.out.println("Errore, il gioco deve avere un prezzo");
                continue;
            }
            return prezzo;
        }
    }

    public int inputAnnoVideogames() {
        while (true) {
            int annoDiUscita = Integer.parseInt(tastiera.nextLine().trim());
            int annoCorrente = LocalDate.now().getYear();

            if (annoDiUscita < 1970 || annoDiUscita > annoCorrente) {
                System.out.println("Errore, l'anno selezionato non è valido");
                continue;
            }
            return annoDiUscita;
        }
    }

    public int inputAnnoBoardgame() {
        while (true) {
            int annoDiUscita = Integer.parseInt(tastiera.nextLine().trim());
            int annoCorrente = LocalDate.now().getYear();

            if (annoDiUscita > annoCorrente) {
                System.out.println("Errore, l'anno selezionato non è valido");
                continue;
            }
            return annoDiUscita;
        }
    }

    public List<String> inputPiattaforma() {
        while (true) {
            String piattaformeInserite = tastiera.nextLine().trim();

            if (piattaformeInserite.isEmpty()) {
                System.out.println("Errore, non hai inserito nulla");
                continue;
            }
            List<String> listaPiattaforme = Arrays.asList(piattaformeInserite.split("[\\s,]+")); //sto dicendo taglia(split) dove trovi virgole e spazi (anche più in fila grazie al +
            return listaPiattaforme;
        }
    }

    public int inputDurata() {
        while (true) {
            int durata = Integer.parseInt(tastiera.nextLine().trim());
            if (durata <= 0) {
                System.out.println("Errore, durata non valida");
            }
            return durata;
        }
    }

    public Genere inputGenere() {
        while (true) {

            try {
                System.out.println("Inserisci uno o più generi tra questi: ");
                for (Genere g : Genere.values()) {
                    System.out.print(g + " ");
                }
                String scelta = tastiera.nextLine().trim().toUpperCase();
                return Genere.valueOf(scelta);

            } catch (IllegalArgumentException e) {
                System.out.println("Errore: Il genere inserito non esiste tra quelli disponibili. Riprova.\n");
            }
        }
    }

    public int inputNgiocatori() {
        while (true) {
            int nGiocatori = Integer.parseInt(tastiera.nextLine().trim());
            if (nGiocatori < 2 || nGiocatori > 10) {
                System.out.println("Errore, numero di giocatori non valido");
            }
            return nGiocatori;
        }
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
        Gioco giocoDaEliminare = listaGiochi.stream().filter(g -> g.getId() == idDaRimuovere).toList().getFirst();
        if (giocoDaEliminare == null) {
            throw new Exception("l'id inserito non è presente nella tua collezione");
        }

        listaGiochi.remove(giocoDaEliminare);
    }

    public void aggiornaGioco() {
        System.out.println("Inserisci l'ID del gioco da modificare:");

        int idDaCercare = Integer.parseInt((tastiera.nextLine()).trim());

        // Optional, con lui evito il doppio ciclo dovuto ad anymatch con la creazione di un if ed else se l'id esiste e poi il getFirst
        Optional<Gioco> giocoOpt = listaGiochi.stream()
                .filter(g -> g.getId() == idDaCercare)
                .findFirst();

        if (giocoOpt.isPresent()) {
            Gioco giocoVecchio = giocoOpt.get();


            System.out.println("Inserisci il nuovo titolo: ");
            String nuovoTitolo = inputTitolo();

            System.out.println("Inserisci un nuovo prezzo: ");
            int nuovoPrezzo = inputPrezzo();


            if (giocoVecchio instanceof Videogiochi) {

                System.out.println("Inserisci nuova data di uscita: ");
                int nuovaData = inputAnnoVideogames();

                System.out.println("Inserisci le nuove piattaforme: ");
                List<String> nuovePiattaforme = inputPiattaforma();

                System.out.println("Inserisci la nuova durata: ");
                int nuovaDurata = inputDurata();

                Genere genere = inputGenere();
                List<Genere> listaGeneri = List.of(genere);

                Videogiochi nuovoVideogioco = new Videogiochi(idDaCercare, nuovoTitolo, nuovaData, nuovoPrezzo, nuovePiattaforme, nuovaDurata, listaGeneri);

                listaGiochi.remove(giocoVecchio);
                listaGiochi.add(nuovoVideogioco);

            } else if (giocoVecchio instanceof BoardGame) {

                System.out.println("Inserisci nuova data di uscita: ");
                int nuovaData = inputAnnoBoardgame();

                System.out.println("Inserisci numero giocatori: ");
                int nuovoNgiocatori = inputNgiocatori();

                System.out.println("Inserisci la nuova durata: ");
                int nuovaDurata = inputDurata();

                BoardGame nuovoBoardGame = new BoardGame(idDaCercare, nuovoTitolo, nuovaData, nuovoPrezzo, nuovoNgiocatori, nuovaDurata);

                listaGiochi.remove(giocoVecchio);
                listaGiochi.add(nuovoBoardGame);
            }

            System.out.println("Gioco sostituito e aggiornato con successo!");

        } else {
            System.out.println("Errore, l'id da te selezionato non esiste.");
        }
    }


    public void statistics() {
        DoubleSummaryStatistics stats = listaGiochi.stream().mapToDouble(Gioco::getPrezzo).summaryStatistics();
        System.out.println(stats);
    }

    @Override
    public String toString() {
        return listaGiochi.stream().map(Gioco::toString).collect(Collectors.joining("\n"));
    }
}
