package vincenzomanfredi;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner tastiera = new Scanner(System.in);


        Gioco gta5 = new Videogiochi(1, "Gta5", 2013, 15.0, List.of("Pc", "Xbox", "PlayStation"), 12, List.of(Genere.Action, Genere.GDR));
        Gioco monopoly = new BoardGame(2, "Monopoly", 2000, 20.0, 6, 90);

        List<Gioco> giochi = List.of(gta5, monopoly);
        Collezione miaCollezione = new Collezione();

        try {
            miaCollezione.aggiungiGioco(gta5);
            miaCollezione.aggiungiGioco(monopoly);

            System.out.println("Gioco aggiunto con successo! Ora nella tua collezione ci sono: \n" + miaCollezione);
        } catch (Exception e) {
            System.out.println("Si è verificato un problema: " + e.getMessage());
            System.out.println(e.getStackTrace().toString());
        }


        int scelta = -1;

        while (scelta != 0) {

            System.out.println("MENU COLLEZIONE");
            System.out.println("Premi 1 per aggiungere un gioco");
            System.out.println("Premi 2 per cercare un gioco tramite id");
            System.out.println("Premi 3 per cercare giochi in base al prezzo");
            System.out.println("Premi 4 per cercare giochi in base al numero di giocatori");
            System.out.println("Premi 5 per rimuovere un gioco tramite id");
            System.out.println("Premi 6 per conoscere le statistiche della collezione");

            System.out.print("Scegli un'opzione: ");
            scelta = tastiera.nextInt();

            switch (scelta) {

                case 1:
                    int sceltaAdd;
                    System.out.println("Premi 1 per aggiungere un Videogioco o 2 per aggiungere un gioco da tavolo");
                    sceltaAdd = tastiera.nextInt();

                    if (sceltaAdd != 1 && sceltaAdd != 2) {
                        System.out.println("Opzione non valida");
                        return;
                    }

                    int id = 0;
                    String titolo = "";
                    double prezzo = 0;
                    int annoUscita = 0;

                    System.out.println("Inserisci un id: ");
                    id = tastiera.nextInt();
                    tastiera.nextLine();  //Titolo veniva saltato andando a prezzo
                    System.out.println("Inserisci un titolo: ");
                    titolo = tastiera.nextLine();
                    System.out.println("Inserisci un prezzo: ");
                    prezzo = tastiera.nextDouble();
                    System.out.println("Inserisci l'anno d'uscita: ");
                    annoUscita = tastiera.nextInt();


                    if (sceltaAdd == 1) {
                        String inputPiattaforma = "";
                        List<String> piattaforma = Arrays.asList(inputPiattaforma.split(",")); //taglia la stringa ad ogni virgola dando come risultato un array di stringhe come vuole l'attributo piattaforma
                        String inputGeneri = "";
                        int durata = 0;
                        System.out.println("Inserisci la piattaforma di gioco: ");
                        inputPiattaforma = tastiera.nextLine();
                        System.out.println("Inserisci la durata del gioco: ");
                        durata = tastiera.nextInt();
                        System.out.println("Inserisci il genere del gioco: ");
                        inputGeneri = tastiera.nextLine();
                        Genere genere = Genere.valueOf(inputGeneri);


                    } else {
                        int nGiocatori = 0;
                        int durataPartita = 0;
                        System.out.println("Inserisci il numero di giocatori di questo gioco da tavolo: ");
                        nGiocatori = tastiera.nextInt();
                        System.out.println("Inserisci la durata media di una partita: ");
                        durataPartita = tastiera.nextInt();

                        BoardGame nuovoBoardGame = new BoardGame(id, titolo, annoUscita, prezzo, nGiocatori, durataPartita);

                        try {
                            miaCollezione.aggiungiGioco(nuovoBoardGame);
                            System.out.println("Gioco aggiunto con successo!");
                        } catch (Exception e) {
                            System.out.println("Si è verificato un problema: " + e.getMessage());
                        }

                    }
                    break;

                case 2:
                    System.out.println("Inserisci l'id da cercare: ");
                    int idCercato = tastiera.nextInt();
                    try {
                        String nomeGioco = miaCollezione.ricercaPerId(idCercato);
                        System.out.println("Il gioco da te cercato è: " + nomeGioco);
                    } catch (Exception e) {
                        System.out.println("Si è verificato un problema:" + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Inserisci un prezzo per filtrare i giochi: ");
                    double prezzoMassimo = tastiera.nextDouble();
                    try {
                        List<String> giochiInferioria = miaCollezione.ricercaPerPrezzo(prezzoMassimo);
                        System.out.println("I giochi inferiori alla cifra inserita sono: " + giochiInferioria);
                    } catch (Exception e) {
                        System.out.println("Si è verificato un problema: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Inserisci il numero di giocatori che fa per te: ");
                    int nGiocatori = tastiera.nextInt();
                    try {
                        List<String> partyGames = miaCollezione.ricercaPerNumGiocatori(nGiocatori);
                        System.out.println("I giochi con questo numero di giocatori sono: " + partyGames);
                    } catch (Exception e) {
                        System.out.println("Si è verificato un problema:" + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Inserisci l'id per rimuovere un gioco: ");
                    int idDaRimuovere = tastiera.nextInt();
                    try {
                        miaCollezione.rimozioneTramiteId(idDaRimuovere);

                    } catch (Exception e) {
                        System.out.println("Si è verificato un problema:" + e.getMessage());
                    }
                    System.out.println("La tua collezione aggiornata è: " + miaCollezione);

                case 6:
                    miaCollezione.statistics();

                case 0:
                    System.out.println("Grazie per aver usato il programma. Arrivederci!");
                    break;

                default:
                    System.out.println("Opzione non valida! Riprova.");
                    break;
            }
        }


    }
}
