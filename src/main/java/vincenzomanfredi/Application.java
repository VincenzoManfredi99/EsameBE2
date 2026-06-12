package vincenzomanfredi;

import java.util.List;

public class Application {

    public static void main(String[] args) {


        Gioco gta5 = new Videogiochi(1, "Gta5", 2013, 15, List.of("Pc", "Xbox", "PlayStation"), 12, List.of(Genere.Action, Genere.GDR));
        Gioco monopoly = new BoardGame(2, "Monopoly", 2000, 20.0, 6, 90);

        List<Gioco> giochi = List.of(gta5, monopoly);
        Collezione miaCollezione = new Collezione();

        try {
            miaCollezione.aggiungiGioco(gta5);
            miaCollezione.aggiungiGioco(monopoly);

            System.out.println("Gioco aggiunto con successo! Ora nella tua collezione ci sono: \n" + miaCollezione);
        } catch (Exception e) {
            System.out.println("Si è verificato un problema: " + e.getMessage());
        }

        try {
            String nomeGioco = miaCollezione.ricercaPerId(3);
            System.out.println("Il gioco da te cercato è: " + nomeGioco);
        } catch (Exception e) {
            System.out.println("Si è verificato un problema:" + e.getMessage());
        }

        try {
            List<String> giochiInferioria = miaCollezione.ricercaPerPrezzo(30);
            System.out.println("I giochi inferiori alla cifra inserita sono: " + giochiInferioria);
        } catch (Exception e) {
            System.out.println("Si è verificato un problema: " + e.getMessage());
        }

        try {
            List<String> partyGames = miaCollezione.ricercaPerNumGiocatori(5);
            System.out.println("I giochi con questo numero di giocatori sono: " + partyGames);
        } catch (Exception e) {
            System.out.println("Si è verificato un problema:" + e.getMessage());
        }

    }
}
