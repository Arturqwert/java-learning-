package PlayersApp.service;

import PlayersApp.Data.DataProviderJSON;
import PlayersApp.Data.DataProviderJSON_Impl;
import PlayersApp.Model.Player;
import PlayersApp.Model.Response;
import PlayersApp.Model.ResponseInt;
import PlayersApp.Model.Statuses;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class PlayerServiceImpl implements PlayerService{

    private final Map<Integer, Player> playersMap;
    private final Set<String> nicknames;
    private final DataProviderJSON provider;
    private int counter = 0;

    public PlayerServiceImpl() {
        provider = new DataProviderJSON_Impl();
        playersMap = new HashMap<>();
        nicknames = new HashSet<>();
        playersFromFileToCollections();
    }

    @Override
    public Response<Collection<Player>> getPlayers() {
        return new Response<>(Statuses.OK, "getCollectionPlayers", playersMap.values());
    }

    @Override
    public Response<Player> getPlayerById(int playerId) {
        playersFromFileToCollections();

        checkPlayerInMap(playerId);
        return new Response<>(Statuses.OK, "getPlayerById", playersMap.get(playerId));
    }

    @Override
    public Response<Integer> createPlayer(String nickname) {
        if(nicknames.contains(nickname))
        {
            return new Response<>(Statuses.BAD_REQUEST, "Player with nick " + nickname + " already exist", -1);
        }
        counter++;
        Player player = new Player(nickname, counter, 0, true);
        playersMap.put(counter, player);
        nicknames.add(nickname);
        savePlayersToFile();
        return new Response<>(Statuses.OK, "player is created", player.getId());
    }

    @Override
    public Response<Player> deletePlayerById(int playerId) {
        playersFromFileToCollections();
        checkPlayerInMap(playerId);
        var res = playersMap.remove(playerId);
        savePlayersToFile();

        return new Response<>(Statuses.OK, "playerDeleted", res);
    }

    @Override
    public Response<Integer> addPoints(int playerId, int points) {
        if(points <= 0)
        {
            return new Response<>(Statuses.BAD_REQUEST, "invalid points amount", -1);
        }
        playersFromFileToCollections();
        checkPlayerInMap(playerId);
        Player player = playersMap.get(playerId);
        player.setPoints(player.getPoints() + points);
        savePlayersToFile();
        return new Response<>(Statuses.OK, points + "points added to " + player.getNick(), points);
    }

    private void playersFromFileToCollections() {
        Collection<Player> players = Collections.emptyList();
        try {
            players = provider.load();
        }
        catch (Exception ex)
        {
            System.err.println("failed to load file " + ex.getMessage());
        }

        for (Player player : players) {
            playersMap.put(player.getId(), player);
            nicknames.add(player.getNick());
            if(player.getId() > counter)
                counter = player.getId();
        }
    }
    private void checkPlayerInMap(int playerId) {
        if(!playersMap.containsKey(playerId))
            throw new NoSuchElementException("No such player with id = " + playerId);
    }

    private void savePlayersToFile()
    {
        try
        {
            provider.save(playersMap.values());
        }
        catch (Exception ex)
        {
            System.err.println("failed to save file");
        }
    }
}
