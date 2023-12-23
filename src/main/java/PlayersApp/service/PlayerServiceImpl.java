package PlayersApp.service;

import PlayersApp.Data.DataProviderJSON;
import PlayersApp.Data.DataProviderJSON_Impl;
import PlayersApp.Model.Player;
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
    }

    @Override
    public Collection<Player> getPlayers() {
        return playersFromFileToCollections();
    }

    @Override
    public Player getPlayerById(int playerId) {
        playersFromFileToCollections();

        checkPlayerInMap(playerId);
        return playersMap.get(playerId);
    }

    @Override
    public int createPlayer(String nickname) {
        if(nicknames.contains(nickname))
        {
            throw new IllegalArgumentException("Player with nick " + nickname + " already exist");
        }
        counter++;
        Player player = new Player(nickname, counter, 0, true);
        playersMap.put(counter, player);
        nicknames.add(nickname);
        savePlayersToFile();
        return player.getId();
    }

    @Override
    public Player deletePlayerById(int playerId) {
        playersFromFileToCollections();
        checkPlayerInMap(playerId);
        var res = playersMap.remove(playerId);
        savePlayersToFile();

        return res;
    }

    @Override
    public int addPoints(int playerId, int points) {
        playersFromFileToCollections();
        checkPlayerInMap(playerId);
        Player player = playersMap.get(playerId);
        player.setPoints(player.getPoints() + points);
        savePlayersToFile();
        return points;
    }

    private Collection<Player> playersFromFileToCollections() {
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
        return players;
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
