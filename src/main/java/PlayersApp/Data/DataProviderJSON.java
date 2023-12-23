package PlayersApp.Data;

import PlayersApp.Model.Player;

import java.io.IOException;
import java.util.Collection;

public interface DataProviderJSON {
    public Collection<Player> load() throws IOException;
    public void save(Collection<Player> players) throws IOException;
}
