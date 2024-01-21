package PlayersApp;
import PlayersApp.Extensions.HtmlReporter;
import PlayersApp.Extensions.PlayersAppExt;
import PlayersApp.Extensions.Watcher;
import PlayersApp.Model.Player;
import PlayersApp.Model.Response;
import PlayersApp.Model.ResponseInt;
import PlayersApp.service.PlayerService;
import PlayersApp.service.PlayerServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("тесты ыы")
@ExtendWith({PlayersAppExt.class, Watcher.class, HtmlReporter.class})
public class JunitPlayersAppTest {

    private static Path path;
    private PlayerService service;

    @BeforeAll
    public static void initPath() {
        path = Path.of("C:\\Users\\artur\\IdeaProjects\\first-java-program\\src\\main\\java\\PlayersApp\\storage\\players.json");
    }

    @BeforeEach
    public void createInstanceService() {
        service = new PlayerServiceImpl();
    }

    @AfterEach
    public void DeleteFileAfterTest() throws IOException {
        System.out.println("additional logics");
    }

    @DisplayName("создание юзеров из getNames.")
    @ParameterizedTest(name = "{index} => nick - {0}")
    @MethodSource("getNames")
    public void shouldCreateFile(String name) {
        service.createPlayer(name);
        assertTrue(Files.exists(path));
    }

    public static String[] getNames()
    {
        return new String[]{"tester", "aboba", "123"};
    }

    @DisplayName("добавление очков игроку.")
    @ParameterizedTest(name =  "{index} => добавили {0} очков")
    @ValueSource(ints = {10, 1, 5})
    @Tag("CRITICAL")
    public void shouldAddPointsToPlayer(int points) {
        Response<Integer> response = service.createPlayer("tester");

        service.addPoints(response.getPayload(), points);
        Response<Player> player = service.getPlayerById(response.getPayload());
        assertEquals(points, player.getPayload().getPoints());
    }
}
