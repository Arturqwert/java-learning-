package serialization;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class PlayerServiceImpl implements PlayerService {

    String path = "C:\\Users\\artur\\IdeaProjects\\first-java-program\\src\\main\\resources\\players.json";
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Player getPlayerById(int id) {
        try{
            List<Player> list = new ArrayList<>();
            list = mapper.readValue(Path.of(path).toFile(), new TypeReference<List<Player>>() {});

            for (var player : list) {
                if(player.id == id)
                    return player;
            }
        }
        catch (IOException exception){
        }
        return null;
    }

    @Override
    public Collection<Player> getPlayers() {
        try{
        List<Player> list = new ArrayList<>();
        list = mapper.readValue(Path.of(path).toFile(), list.getClass());
        return list;
        }
        catch (IOException exception){

        }
        return null;
    }

    @Override
    public int createPlayer(Player player) throws IOException {

        if(Files.exists(Path.of(path)))
        {
            List<Player> list = new ArrayList<>();
            list = mapper.readValue(Path.of(path).toFile(), list.getClass());
            list.add(player);
            mapper.writeValue(Path.of(path).toFile(), list);
            return player.id;
        }

        Player[] arr = {player};
        mapper.writeValue(Path.of(path).toFile(), arr);

        return player.id;
    }

    @Override
    public int createPlayer(String nick) throws IOException {
        return 0;
    }

    @Override
    public Player deletePlayer(int id) {
        try{
            List<Player> list = new ArrayList<>();
            list = mapper.readValue(Path.of(path).toFile(), new TypeReference<List<Player>>() {});

            for (var player : list) {
                if(player.id == id) {
                    list.remove(player);
                    mapper.writeValue(Path.of(path).toFile(), list);
                    return player;
                }
            }
        }
        catch (IOException exception){
        }
        return null;
    }

    @Override
    public int addPoints(int playerId, int points) {
        try{
            List<Player> list = new ArrayList<>();
            list = mapper.readValue(Path.of(path).toFile(), new TypeReference<List<Player>>() {});

            for (var player : list) {
                if(player.id == playerId) {
                    player.points += points;
                    mapper.writeValue(Path.of(path).toFile(), list);
                    return points;
                }
            }
        }
        catch (IOException exception){
        }
        return 0;
    }
}
