package clients;

import clients.apacheHttpClient.InterceptorRequest;
import clients.apacheHttpClient.InterceptorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import clients.model.ToDoTask;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.IOException;

public class JUnitToDoListTest {
    private HttpClient client;
    private final String URL = "https://todo-app-sky.herokuapp.com/";
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void initClient()
    {
        client = HttpClientBuilder.create()
                .addInterceptorLast(new InterceptorRequest())
                .addInterceptorFirst(new InterceptorResponse())
                .build();
    }

    @Test
    @DisplayName("Получение списка задач и проверка границ ответа.")
    public void shouldRecieve200OkStatusAndValidateBoundsJSON() throws IOException {
        HttpGet getTasks = new HttpGet(URL);
        HttpResponse response = client.execute(getTasks);
        String responseAsString = EntityUtils.toString(response.getEntity());
        assertTrue(responseAsString.startsWith("["));
        assertTrue(responseAsString.endsWith("]"));
    }

    @Test
    @DisplayName("Создание задачи")
    public void shouldCreateTask() throws IOException {
        //get task list
        //ToDoTask[] tasks = mapper.readValue(EntityUtils.toString(client.execute(new HttpGet(URL)).getEntity()), ToDoTask[].class);


        ToDoTask task = new ToDoTask();
        task.setTitle("javaObjTask");

        String body = String.format("{\"title\":\"%s\"}", task.title);
        HttpPost post = new HttpPost(URL);
        post.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        HttpResponse response = client.execute(post);
        ToDoTask responseObj = mapper.readValue(EntityUtils.toString(response.getEntity()), ToDoTask.class);
        assertEquals(task.title, responseObj.title);
        assertEquals(201, response.getStatusLine().getStatusCode());
    }
}
