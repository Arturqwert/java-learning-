package PlayersApp.service;

import PlayersApp.Model.Player;
import PlayersApp.Model.Response;
import PlayersApp.Model.ResponseInt;

import java.util.Collection;

public interface PlayerService {

    Response<Collection<Player>> getPlayers();
    Response<Player> getPlayerById(int playerId);
    Response<Integer> createPlayer(String nickname);
    Response<Player> deletePlayerById(int playerId);
    Response<Integer> addPoints(int playerId, int points);
}
