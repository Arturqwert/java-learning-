package PlayersApp.Model;

public enum Statuses {
    OK("ok", 200),
    BAD_REQUEST("bad", 400);

    private String comment;
    private int code;

    Statuses(String comment, int code) {
        this.comment = comment;
        this.code = code;
    }

    public String getComment() {
        return comment;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Statuses{" +
                "comment='" + comment + '\'' +
                ", code=" + code +
                '}';
    }
}
