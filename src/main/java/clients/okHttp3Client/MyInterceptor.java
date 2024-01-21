package clients.okHttp3Client;

import kotlin.Pair;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class MyInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        System.out.println("REQUEST=====>");
        Request req = chain.request();
        System.out.println(req.method() + " " + req.url());
        var headers = req.headers().toMultimap();
        for (String key : headers.keySet()) {
            for (String value : headers.get(key)) {
                System.out.println(key + " : " + value);
            }
        }


        if(req.body() != null)
        {
            Buffer buffer = new Buffer();
            req.body().writeTo(buffer);
            System.out.println(buffer.readUtf8());
        }


        Response response = chain.proceed(req);
        if(response.body() != null) {
            System.out.println("BODY======>");
            System.out.println(response.peekBody(response.body().contentLength()).string());
        }
        else
            System.out.println("Has no body");


        return response;
    }
}
