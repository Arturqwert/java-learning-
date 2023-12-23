package serialization;

import java.io.IOException;
import java.util.Collection;

public interface PlayerService {
    Player getPlayerById(int id);
    Collection<Player> getPlayers();
    int createPlayer(Player player) throws IOException;

    int createPlayer(String nick) throws IOException;

    Player deletePlayer(int id);
    int addPoints(int playerId, int points);
}
