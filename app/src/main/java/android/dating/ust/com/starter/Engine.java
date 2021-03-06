package android.dating.ust.com.starter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Core
 * Created by yaroslavpa on 3/8/2015.
 */
public class Engine {



    public interface SearchListener {
        boolean onFound(SearchItem item, Url url);
    }

    public static final String user_agent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
    public static final String referrer = "http://www.google.com";
    // plain:   https://badoo.com/en/dating/ukraine/kyiv-oblast/kiev/girls/page-2/age-23-27/
    private static final String host = "https://badoo.com";
    private static final String general_params = "/en/dating/ukraine/kyiv-oblast/kiev/girls";
    private static final String custom_params = "/page-%d/age-%d-%d/";
    private static final String css_aim = ".user-card";
    private static final String redundant_pagination_info = " women found";
    private static final String redundant_param = "\\?.*";

    private List<SearchItem> items;
    private SearchListener callback;
    private int minAge = 0;
    private int maxAge = 0;
    private volatile boolean interrupted = false;

    public Engine(List<SearchItem> items, SearchListener callback) {
        this.items = items;
        this.callback = callback;
        for (SearchItem item : items) {
            minAge = minAge == 0 || minAge > item.age() ? item.age() : minAge;
            maxAge = maxAge == 0 || maxAge < item.age() ? item.age() : maxAge;
        }
    }

    public void go() {
        try {
            interrupted = false;
            goThrough();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void interrupt() {
        interrupted = true;
    }

    private void goThrough() throws IOException, InterruptedException {
        for (int age = minAge; age <= maxAge; age++)
            for (int page = 0; !interrupted && page < pagesCount(age); page++)
                for (Element element : page(age, page))
                    for (SearchItem item : items)
                        if (match(element, item))
                            callback.onFound(item, new Url(photo(element), account(element)));
    }

    private int pagesCount(int age) throws IOException, InterruptedException {
        Thread.sleep(300L);
        return extractPagesCount(getDocument(age, 1).select(".pagination-info"));
    }

    private int extractPagesCount(Elements select) {
        return Integer.parseInt(select.text().replace(redundant_pagination_info, "").replace(",", "")) / 20;
    }

    private Elements page(int age, int page) throws IOException {
        return getDocument(age, page).select(css_aim);
    }

    private Document getDocument(int age, int page) throws IOException {
        return Jsoup.connect(host + general_params + customParams(age, page))
                .userAgent(user_agent).referrer(referrer).ignoreContentType(true).get();
    }

    private String customParams(int age, int page) {
        return String.format(custom_params, page, age, age);
    }

    private boolean match(Element element, SearchItem item) {
        return element.select("a.user-name").text().equals(item.name());
    }

    private URL account(Element element) {
        try {
            return new URL(element.select("a.user-name").attr("href").replaceAll(redundant_param, ""));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private URL photo(Element element) {
        try {
            return new URL(element.select("img").attr("src"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

