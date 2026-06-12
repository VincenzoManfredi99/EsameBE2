package vincenzomanfredi;

import java.util.List;

public class Application {

    public static void main(String[] args) {


        Gioco gta5 = new Videogiochi(1, "Gta5", 2013, 15, List.of("Pc", "Xbox", "PlayStation"), 12, List.of(Genere.Action, Genere.GDR));
        Gioco monopoly = new BoardGame(2, "Monopoly", 2000, 20.0, 6, 90);

        List<Gioco> giochi = List.of(gta5, monopoly);

        System.out.println(giochi);


    }
}
