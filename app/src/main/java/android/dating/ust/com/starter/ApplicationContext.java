package android.dating.ust.com.starter;

/**
 * Shared memory between activities
 * Created by yaroslavpa on 3/7/2015.
 */
public class ApplicationContext {

    private static SearchItem item;

    public static SearchItem item() {
        return item;
    }

    public static void item(SearchItem item) {
        ApplicationContext.item = item;
    }
    public static void clearItem() {
        item = null;
    }
}
