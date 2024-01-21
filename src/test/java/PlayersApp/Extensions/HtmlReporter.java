package PlayersApp.Extensions;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HtmlReporter implements TestWatcher, BeforeAllCallback, AfterAllCallback {
    Map<String, String> report;
    private final String head =
            """
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <title>Title</title>
                    </head>
                    <body>
                          <table>
                                <thead>
                                    <th style="background-color:purple">name</th>
                                    <th style="background-color:purple">status</th>
                                </thead>
                                
                                <tbody> 
                    """;
    private final String tail =
            """
                     </tbody>
                          </table> 
                    </body>
                    </html>
                    """;

    private final String path = LocalDateTime.now().toLocalDate() + "report.html";
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        TestWatcher.super.testDisabled(context, reason);
        report.put(context.getDisplayName(), "disabled");
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        TestWatcher.super.testSuccessful(context);
        report.put(context.getDisplayName(), "success");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testAborted(context, cause);
        report.put(context.getDisplayName(), "aborted");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testFailed(context, cause);
        report.put(context.getDisplayName(), "failed");
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {

        String color = null;
        Files.writeString(Path.of(path), head,StandardOpenOption.CREATE);

        for (String status : report.keySet()) {
            if(report.get(status).equals("success"))
                color = "green";
            else
                color = "red";
            String line = "<tr><td>" + status + "</td><td style=\"background-color:" + color + "\">" + report.get(status) + "</td></tr>";

            Files.writeString(Path.of(path), line, StandardOpenOption.APPEND);
        }
        Files.writeString(Path.of(path), tail, StandardOpenOption.APPEND);
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        report = new HashMap<>();
        Files.deleteIfExists(Path.of(path));
    }
}
