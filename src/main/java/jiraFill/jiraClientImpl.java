package jiraFill;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class jiraClientImpl implements jiraClient{
    public static int counter = -1;
    HttpClient client = HttpClientBuilder.create().build();

//    public static void main(String[] args) throws IOException {
//        //get session
//        String URL = "https://jira.astondevs.ru/login.jsp";
//        HttpClient client = HttpClientBuilder.create().build();
//
//        HttpPost post = new HttpPost(URL);
//        post.addHeader("Content-Type", "application/x-www-form-urlencoded");
//      //  post.setEntity(new StringEntity("os_username=a.smarun&os_password=BEq%23Eqi74IVy&os_destination=&user_role=&atl_token=&login=Log+In"));
//
//        List<NameValuePair> params1 = new ArrayList<>(5);
//        params1.add(new BasicNameValuePair("os_username", "a.smarun"));
//        params1.add(new BasicNameValuePair("os_password", "BEq#Eqi74IVy"));
//        params1.add(new BasicNameValuePair("os_destination", ""));
//        params1.add(new BasicNameValuePair("user_role", ""));
//        params1.add(new BasicNameValuePair("atl_token", ""));
//        params1.add(new BasicNameValuePair("login", "Log In"));
//
//        post.setEntity(new UrlEncodedFormEntity(params1, "UTF-8"));
//
//
//        HttpResponse response = client.execute(post);
//        String session = getHeaderValue(response);
//
//        //get token
//        String URLtoken = "https://jira.astondevs.ru/";
//        HttpGet getToken = new HttpGet(URLtoken);
//        getToken.addHeader("Set-Cookie", session);
//
//        HttpResponse getTokenResponse = client.execute(getToken);
//        String token = getHeaderValue(getTokenResponse);
//
//
//        //create work log
//        String URLcreate = "https://jira.astondevs.ru/secure/CreateWorklog.jspa";
//        HttpPost createWorkLog = new HttpPost(URLcreate);
//        createWorkLog.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//        createWorkLog.addHeader("Referer", "https://jira.astondevs.ru/browse/ALFABANK-143");
//        createWorkLog.addHeader("Cookie", session + "; " + token);
//
//        String tokenParsed = token.replace("atlassian.xsrf.token=", "");
//
//        List<NameValuePair> params = new ArrayList<>(2);
//        params.add(new BasicNameValuePair("id", "819838"));
//        params.add(new BasicNameValuePair("timeLogged", "4h"));
//        params.add(new BasicNameValuePair("startDate", "3/Feb/24 19:41"));
//        params.add(new BasicNameValuePair("adjustEstimate", "auto"));
//        params.add(new BasicNameValuePair("comment", "java"));
//        params.add(new BasicNameValuePair("atl_token", tokenParsed));
//
//        createWorkLog.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//
//        HttpResponse responseCreate =  client.execute(createWorkLog);
//   }



    @Override
    public String getSession() throws IOException {

        String URL = "https://jira.astondevs.ru/login.jsp";
        HttpPost post = new HttpPost(URL);
        post.addHeader("Content-Type", "application/x-www-form-urlencoded");
        //post.setEntity(new StringEntity("os_username=a.smarun&os_password=BEq%23Eqi74IVy&os_destination=&user_role=&atl_token=&login=Log+In"));
        List<NameValuePair> params1 = new ArrayList<>(5);
        params1.add(new BasicNameValuePair("os_username", "a.smarun"));
        params1.add(new BasicNameValuePair("os_password", "BEq#Eqi74IVy"));
        params1.add(new BasicNameValuePair("os_destination", ""));
        params1.add(new BasicNameValuePair("user_role", ""));
        params1.add(new BasicNameValuePair("atl_token", ""));
        params1.add(new BasicNameValuePair("login", "Log In"));

        post.setEntity(new UrlEncodedFormEntity(params1, "UTF-8"));

        HttpResponse response = client.execute(post);
        String session = getHeaderValue(response);
        return session;
    }

    @Override
    public String getToken(String session) throws IOException {

        String URLtoken = "https://jira.astondevs.ru/";
        HttpGet getToken = new HttpGet(URLtoken);
        getToken.addHeader("Set-Cookie", session);

        HttpResponse getTokenResponse = client.execute(getToken);
        String token = getHeaderValue(getTokenResponse);
        return token;
    }

    @Override
    public void createWorkLog(String token, String session) throws IOException {

        String date = generateDate() + " " + generateTime();
        var comments = getComments();
        Random randomizer = new Random();
        String comment = comments.get(randomizer.nextInt(comments.size()));

        String URLcreate = "https://jira.astondevs.ru/secure/CreateWorklog.jspa";
        HttpPost createWorkLog = new HttpPost(URLcreate);
        createWorkLog.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        createWorkLog.addHeader("Referer", "https://jira.astondevs.ru/browse/ALFABANK-143");
        createWorkLog.addHeader("Cookie", session + "; " + token);

        String tokenParsed = token.replace("atlassian.xsrf.token=", "");

        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("id", "819838"));
        params.add(new BasicNameValuePair("timeLogged", "8h"));
        params.add(new BasicNameValuePair("startDate", date));
        params.add(new BasicNameValuePair("adjustEstimate", "auto"));
        params.add(new BasicNameValuePair("comment", comment));
        params.add(new BasicNameValuePair("atl_token", tokenParsed));

        createWorkLog.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse responseCreate =  client.execute(createWorkLog);
    }

    private String generateDate()
    {
        var dates = getDates();
        if(dates.size() - 1 > counter)
        {
            counter++;
            return dates.get(counter);
        }
        else
            System.err.println("out of bound dates list");
        return null;
    }

    private String generateTime()
    {
//        var comments = getComments();
//        var len = comments.size();
//        var number = Math.floor(Math.random() * len);

        int randomMunit = (int) Math.floor(Math.random() * (59 - 10) + 10);
        int randomHour = (int) Math.floor(Math.random() * (22 - 17) + 17);

        var time = randomHour + ":" + randomMunit;
        return time;
    }

    private List<String> getComments()
    {
        return new ArrayList<>(Arrays.asList(
                "Встреча с бизнесом, тест доработок",
                "Митап по развитию тестирования, автотесты",
                "Оценка релиза, формирование тест-кейсов",
                "Автотесты",
                "Тестирование доработок",
                "Регрессионное тестирование",
                "Формирование отчета по тестированию",
                "Рефакторинг проекта автотестов",
                "Автоматизация тестовой структуры",
                "Актуализация документации",
                "Ретест дефектов"
        ));
//        comments.add("Встреча с бизнесом, тест доработок");
    }
    @Override
    public List<String> getDates()
    {
        return new ArrayList<>(Arrays.asList(
                "01/Feb/24",
                "02/Feb/24",

                "05/Feb/24",
                "06/Feb/24",
                "07/Feb/24",
                "08/Feb/24",
                "09/Feb/24",

                "12/Feb/24",
                "13/Feb/24",
                "14/Feb/24",
                "15/Feb/24",
                "16/Feb/24",

                "19/Feb/24",
                "20/Feb/24",
                "21/Feb/24",
                "22/Feb/24",

                "26/Feb/24",
                "27/Feb/24",
                "28/Feb/24",
                "29/Feb/24"
        ));
    }
    @NotNull
    private String getHeaderValue(HttpResponse response) {
        String header = Arrays.stream(response.getHeaders("Set-Cookie")).findFirst().get().getValue();
        String value = header.substring(0, header.indexOf(';'));
        return value;
    }
}