package PlayersApp.Extensions;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.nio.file.Files;
import java.nio.file.Path;

public class PlayersAppExt implements AfterEachCallback {
    private final Path path = Path.of("C:\\Users\\artur\\IdeaProjects\\first-java-program\\src\\main\\java\\PlayersApp\\storage\\players.json");
    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        Files.deleteIfExists(path);
        System.out.println("a-callback");
    }
}
