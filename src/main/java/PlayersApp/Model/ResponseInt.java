package PlayersApp.Model;

import java.util.Objects;

public class ResponseInt {
    private Statuses status;
    private String message;
    private int value;

    @Override
    public String toString() {
        return "ResponseInt{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseInt that = (ResponseInt) o;
        return value == that.value && Objects.equals(status, that.status) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, value);
    }

    public Statuses getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getValue() {
        return value;
    }

    public ResponseInt(Statuses status, String message, int value) {
        this.status = status;
        this.message = message;
        this.value = value;
    }
}
