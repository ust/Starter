package android.dating.ust.com.starter;

import android.test.InstrumentationTestCase;

import java.util.Collections;

/**
 * core test-harness
 * Created by yaroslavpa on 3/8/2015.
 */
public class EngineTest extends InstrumentationTestCase {

    public void test() {
        final SearchItem[] found = new SearchItem[1];
        new Engine(Collections.singletonList(new SearchItem(System.currentTimeMillis(), "Анна", (byte) 23)),
                new Engine.SearchListener() {
                    @Override
                    public boolean onFound(SearchItem item, Url url) {
                        found[0] = item;
                        return false;
                    }
                }
        ).go();
        assertNotNull("oops", found[0]);
    }
}
