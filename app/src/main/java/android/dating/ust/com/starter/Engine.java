package android.dating.ust.com.starter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

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
    // plain:   https://badoo.com/en/dating/ukraine/kyiv-oblast/kiev/girls/page-2/age-23-27/
    private static final String host = "https://badoo.com";
    private static final String general_params = "/en/dating/ukraine/kyiv-oblast/kiev/girls";
    private static final String custom_params = "/page-%d/age-%d-%d/";
    private static final String css_aim = ".user-name";
    private static final int max_pages = 1000;

    private List<SearchItem> items;
    private SearchListener callback;
    private int minAge;
    private int maxAge;

    public Engine(List<SearchItem> items, SearchListener callback) {
        this.items = items;
        // fill ages
        this.callback = callback;
    }

    public void go() {
        try {
            goThrough();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goThrough() throws IOException {
        for (int page = 0; page < max_pages; page++)
            for (Element element : Jsoup.connect(host + general_params + customParams(page)).get().select(css_aim))
                for (SearchItem item : items)
                    if (match(element, item))
                        callback.onFound(item, new Url(photo(element), account(element)));
    }

    private String customParams(int page) {
        return String.format(custom_params, page, minAge, maxAge);
    }

    private boolean match(Element element, SearchItem item) {
        return element.text().equals(item.name());
    }

    private URL account(Element element) {
        try {
            return new URL(extractUrl(element));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String extractUrl(Element element) {
        return element.attr("href").replaceAll("\\?.*", "");
    }

    private URL photo(Element element) {
        return null;
    }

    private String ageRange() {
        return "" + minAge + "" + maxAge;
    }

}

