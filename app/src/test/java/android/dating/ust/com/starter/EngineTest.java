package android.dating.ust.com.starter;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertNotNull;

/**
 * core test-harness
 * Created by yaroslavpa on 3/8/2015.
 */
public class EngineTest {

    @Test
    public void test() {
        final SearchItem[] found = new SearchItem[1];
        new Engine(Collections.singletonList(new SearchItem(System.currentTimeMillis(), "Аня", (byte) 25)),
                new Engine.SearchListener() {
                    @Override
                    public boolean onFound(SearchItem item, Url url) {
                        System.out.println(url);
                        found[0] = item;
                        return false;
                    }
                }
        ).go();
        assertNotNull("oops", found[0]);
    }
}
