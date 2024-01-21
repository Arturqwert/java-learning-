package clients;

import clients.okHttp3Client.ToDoListClientImpl;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OkHttp3BusinessTests {

    private final String URL = "https://todo-app-sky.herokuapp.com/";
    ToDoListClientImpl client;

    @BeforeEach
    public void SetUp()
    {
        client = new ToDoListClientImpl(URL);
    }

    @Test
    public void shouldCreateTask() throws IOException {
        var tasksBefore = client.getAll();
        String title = "javaTasKBusiness";
        var newTask = client.create(title);
        var tasksAfter = client.getAll();

        assertEquals(newTask.getTitle(), title);
        assertEquals(tasksAfter.size() - tasksBefore.size(), 1);
    }
}
