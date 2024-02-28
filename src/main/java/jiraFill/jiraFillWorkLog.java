package jiraFill;

import java.io.IOException;

public class jiraFillWorkLog {
    public static void main(String[] args) throws IOException {

        jiraClient client = new jiraClientImpl();

        String session = client.getSession();
        String token = client.getToken(session);
        int size = client.getDates().size();
        for(int i = 0; i < size; i++) {
            client.createWorkLog(token, session);
        }
        System.out.println("completed");

    }
}
