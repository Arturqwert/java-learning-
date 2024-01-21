package clients.okHttp3Client;

import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class ToDoListClient {

    public static void main(String[] args) throws IOException {

        String URL = "https://todo-app-sky.herokuapp.com/";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request req = new Request.Builder().get().url(URL).build();
        Response res = client.newCall(req).execute();

        System.out.println(res.body().string());


        MediaType mediaType = MediaType.parse("application/json; charset=UTF-8");
        String body = "{\"title\":\"javaTask\"}";
        RequestBody requestBody = RequestBody.create(body, mediaType);
        Request post = new Request.Builder().post(requestBody).url(URL).build();
        Response response = client.newCall(post).execute();

        System.out.println(response.body().string());
    }

}
