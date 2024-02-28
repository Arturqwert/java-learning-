package db.api;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class requestInterceptor implements HttpRequestInterceptor {
    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        System.out.println(httpRequest.getRequestLine());


        System.out.println("\nREQUEST HEADERS========>\n");
        for (Header header : httpRequest.getAllHeaders()) {
            System.out.println(header);
        }
    }
}
