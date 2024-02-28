package db.ext;

import db.api.CompanyService;
import db.api.CompanyServiceImpl;
import db.api.requestInterceptor;
import db.api.responseInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CompanyServiceResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CompanyService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        String URL = "https://x-clients-be.onrender.com/company";
        HttpClient client = HttpClientBuilder
                .create()
                .addInterceptorLast(new requestInterceptor())
                .addInterceptorLast(new responseInterceptor())
                .build();
        return new CompanyServiceImpl(client, URL);
    }
}