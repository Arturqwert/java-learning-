package clients.apacheHttpClient;

import clients.ToDoClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import clients.model.ToDoTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ToDoClientImpl implements ToDoClient {

    private final String URL;
    HttpClient client;
    ObjectMapper mapper;
    public ToDoClientImpl(String url) {
        URL = url;
        client = HttpClientBuilder
                .create()
                .addInterceptorFirst(new InterceptorResponse())
                .addInterceptorLast(new InterceptorRequest())
                .build();
        mapper = new ObjectMapper();
    }

    @Override
    public List<ToDoTask> getAll() throws IOException {
        HttpGet get = new HttpGet(URL);
        HttpResponse res = client.execute(get);
        String resAsString = EntityUtils.toString(res.getEntity());
        List<ToDoTask> tasks = mapper.readValue(resAsString, new TypeReference<List<ToDoTask>>() {});

        return tasks;
    }

    @Override
    public ToDoTask getById(int id) throws IOException {
        HttpGet getTask = new HttpGet(URL + id);
        return mapper.readValue(EntityUtils.toString(client.execute(getTask).getEntity()), ToDoTask.class);
    }

    @Override
    public ToDoTask create(String title) throws IOException {
        HttpPost create = new HttpPost(URL);
        String field = String.format("{\"title\": \"%s\"}", title);
        StringEntity body = new StringEntity(field, ContentType.APPLICATION_JSON);
        create.setEntity(body);
        HttpResponse res = client.execute(create);
        return mapper.readValue(EntityUtils.toString(res.getEntity()), ToDoTask.class);
    }

    @Override
    public void delete(int id) throws IOException {
        HttpDelete delete = new HttpDelete(URL + id);
        client.execute(delete);
    }

    @Override
    public ToDoTask renameById(int id, String title) throws IOException {
        HttpPatch patch = new HttpPatch(URL + id);
        patch.setEntity(new StringEntity("{\"title\":\"" + title + "\"}"));
        HttpResponse res = client.execute(patch);
        var renamedTask = mapper.readValue(EntityUtils.toString(res.getEntity()), ToDoTask.class);

        return renamedTask;
    }

    @Override
    public ToDoTask markCompletedById(int id, boolean completed) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
