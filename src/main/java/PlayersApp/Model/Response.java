package PlayersApp.Model;

import java.util.Objects;

public class Response <T>{
    private Statuses status;
    private String message;
    private T payload;

    public Response(Statuses status, String message, T payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", payload=" + payload +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response<?> response = (Response<?>) o;
        return status == response.status && Objects.equals(message, response.message) && Objects.equals(payload, response.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, payload);
    }

    public Statuses getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getPayload() {
        return payload;
    }
}
