package clients.apacheHttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class InterceptorResponse implements HttpResponseInterceptor {
    @Override
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        //System.out.println(EntityUtils.toString(httpResponse.getEntity()));

        if(httpResponse.getEntity() != null) {
            HttpEntity entity = httpResponse.getEntity();
            String body = EntityUtils.toString(entity);
            System.out.println("response body\n");
            System.out.println(body);

            StringEntity newBody = new StringEntity(body, ContentType.APPLICATION_JSON);
            httpResponse.setEntity(newBody);
        }
        else
            System.out.println("no response body");
    }
}
