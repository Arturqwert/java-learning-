package jiraFill;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface jiraClient {
    public String getSession() throws IOException;
    public String getToken(String session) throws IOException;
    public void createWorkLog(String token, String session) throws IOException;
    public List<String> getDates();
}
