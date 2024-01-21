package clients.apacheHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import clients.model.ToDoTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class ToDoListClient {
    static String URL = "https://todo-app-sky.herokuapp.com/";

    public static void main(String[] args) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();

        //Get all
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(URL);
        HttpResponse res = client.execute(get);

        ToDoTask[] tasks = mapper.readValue(EntityUtils.toString(res.getEntity()), ToDoTask[].class);

        for (ToDoTask task : tasks) {
            System.out.println(task.toString());
        }

        System.out.println();
        //Get by id
        printResponse(client.execute(new HttpGet(URL + tasks[0].getId())).getEntity());
        System.out.println(tasks.length);
        //System.out.println(EntityUtils.toString(client.execute(new HttpGet(URL + tasks[0].getId())).getEntity()));

        //POST task
//        HttpPost post = new HttpPost(URL);
//
//        post.addHeader("Content-Type", "application/json");
//        StringEntity body = new StringEntity(String.format("{\"title\": \"javaTask %d\"}", new Random().nextInt(20)));
//        post.setEntity(body);
//
//        printResponse(client.execute(post).getEntity());

        //DELETE task
        HttpDelete delete = new HttpDelete(URL + Arrays.stream(tasks).findFirst().get().getId());
        client.execute(delete);
        tasks = mapper.readValue(EntityUtils.toString(client.execute(get).getEntity()), ToDoTask[].class);
        System.out.println(tasks.length);

        //PATCH task
        HttpPatch patch = new HttpPatch(URL + Arrays.stream(tasks).findFirst().get().getId());
        patch.addHeader("Content-Type", "application/json");
        ToDoTask task = new ToDoTask();
        task.setTitle("NewTitle");
        String taskAsString = mapper.writeValueAsString(task);
        StringEntity entity = new StringEntity(taskAsString);
        patch.setEntity(entity);

        printResponse(client.execute(patch).getEntity());


    }

    public static void printResponse(HttpEntity entity) throws IOException {
        System.out.println(EntityUtils.toString(entity));
    }
}
