package com.myakishev.magistracyTracker.service;

import com.myakishev.magistracyTracker.model.Student;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HtmlService {

    public List<Student> getNewStudents() throws JSONException, IOException {
        return getStudents(getUrlForGetStudents());
    }

    private String getUrlForGetStudents() throws IOException, JSONException {
        final URL url = new URL("https://urfu.ru/api/ratings/info/57/780/");
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        final BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "UTF-8"));
        final String inputLine;
        final StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        final JSONObject json = new JSONObject(content.toString());
        final String pageName = json.getString("url");
        return "https://urfu.ru" + pageName;
    }

    private List<Student> getStudents(String urlForRequest) throws IOException {
        final URL url = new URL(urlForRequest);
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "PostmanRuntime/7.32.3");
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("Postman-Token", "81cf85a6-7459-4a81-9814-3d1e12208306");
        con.setRequestProperty("Host", "urfu.ru");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Cookie", "urfu-client-session-cookie=1779c91b415f0e5082491a5f18991a246dd73da0bed99e57b76701b919817203733e69b7ee51f3b9b34034081aa8b4ed");
        con.setRequestMethod("GET");
        final BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        final String response = in.lines().collect(Collectors.joining(System.lineSeparator()));
        return parseHtmlToStudentList(response);
    }

    private List<Student> parseHtmlToStudentList(String html) {
        final Document doc = Jsoup.parse(html);
        final Element table = doc.select("table").get(27);
        final Elements rows = table.select("tr");
        rows.remove(0);
        final ArrayList<Student> students = new ArrayList<>();
        for (Element row : rows) {
            Elements cols = row.select("td");
            Student student = new Student();
            student.setNumberInList(Integer.parseInt(cols.get(0).getElementsByTag("td").text()));
            student.setSnils(cols.get(1).getElementsByTag("td").text());
            student.setTestPoints(Integer.parseInt(cols.get(5).getElementsByTag("td").text().split(" ")[0]));
            String additionalPoints = cols.get(6).getElementsByTag("td").text();
            if (!additionalPoints.equals("")) {
                student.setAdditionalPoints(Integer.parseInt(additionalPoints));
            }
            student.setSumPoints(Integer.parseInt(cols.get(7).getElementsByTag("td").text()));
            students.add(student);
        }
        return students;
    }
}
