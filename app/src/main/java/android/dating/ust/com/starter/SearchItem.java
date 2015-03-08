package android.dating.ust.com.starter;

import android.content.Intent;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * model
 * Created by Ярослав on 10.02.2015.
 */
public class SearchItem {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";

    private Long id;
    private String name;
    private Byte age;
    private List<Url> urls = new CopyOnWriteArrayList<>();

    public SearchItem(Long id, String name, Byte age) {
        this(id, name, age, null);
    }

    public SearchItem(Long id, String name, Byte age, List<Url> urls) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.urls.addAll(urls);
    }

    public static SearchItem from(Intent intent) {
        Long id = (Long) intent.getSerializableExtra(ID);
        String name = intent.getStringExtra(NAME);
        Byte age = (Byte) intent.getSerializableExtra(AGE);
        return id != null ? new SearchItem(id, name, age) : null;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Byte age() {
        return age;
    }

    public List<Url> urls() {
        return urls;
    }

    public Intent putExtras(Intent intent) {
        return intent.putExtra(ID, id).putExtra(NAME, name).putExtra(AGE, age);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("age", age)
                .toString();
    }
}
