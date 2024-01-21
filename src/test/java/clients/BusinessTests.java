package clients;

import clients.Provider.Provider;
import clients.apacheHttpClient.ToDoClientImpl;
import clients.model.ToDoTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessTests {

    ToDoClient client;
    private final String URL = "https://todo-app-sky.herokuapp.com/";

    @BeforeEach
    public void setUp()
    {
        client = new ToDoClientImpl(URL);
    }

    @Test
    public void shouldCreateTask() throws IOException {
        //получить список задач
        List<ToDoTask> tasksBefore = client.getAll();
        //создать задачу
        String title = "javaTask";
        ToDoTask newTask = client.create(title);
        //проверить отображение задачи в списке
        assertFalse(newTask.getUrl().isBlank());
        assertFalse(newTask.isCompleted());
        assertTrue(newTask.getId() > 0);
        List<ToDoTask> tasksAfter = client.getAll();
        assertEquals(1, tasksAfter.size() - tasksBefore.size());
    }

    @ExtendWith(Provider.class)
    @Test
    public void shouldRenameTask(ToDoTask newTask) throws IOException {
        //var newTask = client.create("newJavaTask");
        String newTitle = "newTitle";
        var renamedTask = client.renameById(newTask.getId(), newTitle);
        var renamedTaskFromList = client.getById(renamedTask.getId());

        assertEquals(newTitle, renamedTaskFromList.title);

        //client.delete(renamedTask.getId());



    }

}
