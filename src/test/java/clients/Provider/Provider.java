package clients.Provider;

import clients.apacheHttpClient.ToDoClientImpl;
import clients.model.ToDoTask;
import org.junit.jupiter.api.extension.*;

import java.io.IOException;

public class Provider implements ParameterResolver, AfterEachCallback {

    ToDoClientImpl client;
    int id;
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(ToDoTask.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        System.out.println("resolver");
        client = new ToDoClientImpl("https://todo-app-sky.herokuapp.com/");
        ToDoTask task;
        try {
            id = client.create("resolverTask").getId();
            task = client.getById(id);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return task;
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        client.delete(id);
    }
}
