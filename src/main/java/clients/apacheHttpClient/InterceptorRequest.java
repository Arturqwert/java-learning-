package clients.apacheHttpClient;

import org.apache.http.*;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class InterceptorRequest implements HttpRequestInterceptor {
    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        System.out.println(httpRequest.getRequestLine());
        for (Header header : httpRequest.getAllHeaders()) {
            System.out.println(header);
        }

        if (httpRequest.getRequestLine().getMethod() != "GET"
                && httpRequest.getRequestLine().getMethod() != "DELETE") {
            HttpEntityEnclosingRequest req = (HttpEntityEnclosingRequest) httpRequest;
            System.out.println("request body\n");
            System.out.println(EntityUtils.toString(req.getEntity()));
        }
        else
        {
            System.out.println("request has no body.");
        }
    }
}
