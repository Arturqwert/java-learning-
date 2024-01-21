package clients;

import clients.model.ToDoTask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ToDoClient {
    //get all
    List<ToDoTask> getAll() throws IOException;
    //get by id
    ToDoTask getById(int id) throws IOException;
    //create
    ToDoTask create(String title) throws IOException;
    //delete by id
    void delete(int id) throws IOException;
    //rename by id
    ToDoTask renameById(int id, String title) throws IOException;
    //mark completed
    ToDoTask markCompletedById(int id, boolean completed);
    //delete all
    void deleteAll();
}
