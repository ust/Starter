package android.dating.ust.com.starter;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.net.URL;

/**
 * Contains collected info regarding search item
 * Created by yaroslavpa on 3/1/2015.
 */
public class Url {
    private URL account;
    private URL photo;
    private Boolean skipped;

    public Url(URL photo, URL account) {
        this.photo = photo;
        this.account = account;
    }

    public URL account() {
        return account;
    }

    public URL photo() {
        return photo;
    }

    public Boolean skipped() {
        return skipped;
    }

    public Url skip() {
        Url url = new Url(account, photo);
        url.skipped = true;
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Url url = (Url) o;

        return account.equals(url.account)
                && !(photo != null ? !photo.equals(url.photo) : url.photo != null)
                && !(skipped != null ? !skipped.equals(url.skipped) : url.skipped != null);

    }

    @Override
    public int hashCode() {
        int result = account.hashCode();
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (skipped != null ? skipped.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("account", account)
                .append("photo", photo)
                .append("skipped", skipped)
                .toString();
    }
}
