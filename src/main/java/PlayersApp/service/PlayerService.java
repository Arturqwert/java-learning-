package PlayersApp.service;

import PlayersApp.Model.Player;

import java.util.Collection;

public interface PlayerService {

    Collection<Player> getPlayers();
    Player getPlayerById(int playerId);
    int createPlayer(String nickname);
    Player deletePlayerById(int playerId);
    int addPoints(int playerId, int points);
}
