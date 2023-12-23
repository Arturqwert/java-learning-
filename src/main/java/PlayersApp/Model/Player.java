package PlayersApp.Model;

import java.util.Objects;

public class Player {
    private String nick;
    private int id;

    private int points;
    private boolean isOnline;

    public Player() {

    }

    @Override
    public String toString() {
        return "Player{" +
                "nick='" + nick + '\'' +
                ", id=" + id +
                ", points=" + points +
                ", isOnline=" + isOnline +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && points == player.points && isOnline == player.isOnline && Objects.equals(nick, player.nick);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nick, id, points, isOnline);
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public Player(String nick, int id, int points, boolean isOnline) {
        this.nick = nick;
        this.id = id;
        this.points = points;
        this.isOnline = isOnline;
    }
}
