package clients.okHttp3Client;

import clients.ToDoClient;
import clients.model.ToDoTask;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class ToDoListClientImpl implements ToDoClient {

    private final String URL;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public ToDoListClientImpl(String URL) {
        this.URL = URL;
        client = new OkHttpClient.Builder().addNetworkInterceptor(new MyInterceptor()).build();
        mapper = new ObjectMapper();
    }

    @Override
    public List<ToDoTask> getAll() throws IOException {
        Request req = new Request.Builder().get().url(URL).build();
        Response res = client.newCall(req).execute();
        List<ToDoTask> tasks = mapper.readValue(res.body().string(), new TypeReference<List<ToDoTask>>() {});

        return tasks;
    }

    @Override
    public ToDoTask getById(int id) {
        return null;
    }

    @Override
    public ToDoTask create(String title) throws IOException {
        String body = "{\"title\":\"" + title +"\"}";
        RequestBody requestBody = RequestBody.create(body, MediaType.parse("application/json; charset=UTF-8"));
        Request req = new Request.Builder().post(requestBody).url(URL).build();

        Response res = client.newCall(req).execute();
        ToDoTask newTask = mapper.readValue(res.body().string(), ToDoTask.class);

        return newTask;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ToDoTask renameById(int id, String title) {
        return null;
    }

    @Override
    public ToDoTask markCompletedById(int id, boolean completed) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
