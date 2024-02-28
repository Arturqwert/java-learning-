package db.api;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

public class responseInterceptor implements HttpResponseInterceptor {
    @Override
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        System.out.println(httpResponse.getStatusLine());


        System.out.println("\nRESPONSE HEADERS============>\n");
        for (Header header : httpResponse.getAllHeaders()) {
            System.out.println(header);
        }

        if(httpResponse.getEntity() != null)
        {
            String body = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("\nBODY===>\n\n" + body);

            StringEntity newBody = new StringEntity(body, ContentType.APPLICATION_JSON);
            httpResponse.setEntity(newBody);
        }
        else
        {
            System.err.println("request has no body");
        }


    }
}
