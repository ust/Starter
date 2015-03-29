package android.dating.ust.com.starter;

import android.os.Handler;
import android.os.Message;

import java.util.Collections;

/**
 * takes care about engine Created by yaroslavpa on 3/29/2015.
 */
public class SearchTask implements Runnable {

    public enum Messages {
        progress, found, finish
    }

    private Handler handler;
    private Engine engine;
    private volatile boolean running = false;

    public SearchTask(Handler handler, SearchItem item) {
        this.handler = handler;
        this.engine = new Engine(Collections.singletonList(item), new Engine.SearchListener() {
            @Override
            public boolean onFound(SearchItem item, Url url) {
                return found(url);
            }
        });
    }

    private boolean found(Url url) {
        Message message = handler.obtainMessage(Messages.found.ordinal(), url);
        handler.sendMessage(message);
        return true;
    }

    public void start() {
        if (!running) {
            new Thread(this).start();
            running = true;
        }
    }

    public void pause() {
    }

    public void resume() {
    }

    public void stop() {
        engine.interrupt();
    }

    @Override
    public void run() {
        engine.go();
    }

}
