package clients.model;

import java.util.Objects;

public class ToDoTask {
    public int id;
    public String title;
    public boolean completed;
    public int order;
    public String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ToDoTask() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoTask toDoTask = (ToDoTask) o;
        return id == toDoTask.id && completed == toDoTask.completed && order == toDoTask.order && Objects.equals(title, toDoTask.title) && Objects.equals(url, toDoTask.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, completed, order, url);
    }

    @Override
    public String toString() {
        return "ToDoTask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", order=" + order +
                ", url='" + url + '\'' +
                '}';
    }
}
