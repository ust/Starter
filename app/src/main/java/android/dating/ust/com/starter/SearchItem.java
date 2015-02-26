package android.dating.ust.com.starter;

/**
 * model
 * Created by Ярослав on 10.02.2015.
 */
public class SearchItem {
    private Integer id;
    private String name;
    private Byte age;

    public SearchItem(Integer id, String name, Byte age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Byte age() {
        return age;
    }
}
