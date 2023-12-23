package serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Player {
    int id;
    String nick;
    int points;
    boolean isOnline;

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", points=" + points +
                ", isOnline=" + isOnline +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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

    public Player(int id, String nick, int points, boolean isOnline) {
        this.id = id;
        this.nick = nick;
        this.points = points;
        this.isOnline = isOnline;
    }

    public Player()
    {

    }

}
