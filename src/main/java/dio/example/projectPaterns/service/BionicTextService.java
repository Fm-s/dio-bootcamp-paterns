package dio.example.projectPaterns.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BionicTextService {
    @Autowired
    private Environment env;
    private String postText;

    public String getBionicText() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://bionic-reading1.p.rapidapi.com/convert").headers(getHeaders()).data("content", postText).data("response_type", "html").data("request_type", "html").data("fixation", "1").data("saccade", "10").postDataCharset("UTF-8").method(Connection.Method.POST).post();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (doc != null) {
            return responseToString(doc);
        }
        return "";
    }

    private String responseToString(Document doc) {
        Optional<Element> element = Optional.ofNullable(doc.body().getElementsByClass("bionic-reader-container").first());
        if (element.isPresent()) {
            String html = element.get().html();
            int removeCommentIndex = html.indexOf("<!--") - 1;
            if (removeCommentIndex > -2) {
                return html.substring(0, removeCommentIndex);
            }
        }
        return "";
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/x-www-form-urlencoded");
        headers.put("X-RapidAPI-Key", env.getProperty("BIONIC_API_KEY"));
        headers.put("X-RapidAPI-Host", "bionic-reading1.p.rapidapi.com");
        return headers;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
}
