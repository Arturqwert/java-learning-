package serialization;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        PlayerService service = new PlayerServiceImpl();

        //System.out.println(service.createPlayer(new Player(2, "Alberka", 2, true)));

        OutputPlayers(service);

        service.addPoints(3, 10);

        OutputPlayers(service);

    }

    private static void OutputPlayers(PlayerService service) {
        for (Object o : service.getPlayers().toArray()) {
            System.out.println(o);
        }
    }

}
