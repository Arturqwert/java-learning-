package PlayersApp.Data;

import PlayersApp.Model.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public class DataProviderJSON_Impl implements DataProviderJSON{

    private final ObjectMapper mapper;
    public static String path;
    public DataProviderJSON_Impl() {
       mapper = new ObjectMapper();
       path = "C:\\Users\\artur\\IdeaProjects\\first-java-program\\src\\main\\java\\PlayersApp\\storage\\players.json";
    }

    @Override
    public Collection<Player> load() throws IOException {
        return mapper.readValue(Path.of(path).toFile(), new TypeReference<Collection<Player>>(){});
    }

    @Override
    public void save(Collection<Player> players) throws IOException {
        mapper.writeValue(Path.of(path).toFile(), players);
    }
}
