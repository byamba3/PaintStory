package bryan.paintstory;

/**
 * Created by byamba on 4/22/17.
 */

public class StoryItem {
    private String title;
    private String imageURL;
    private long date;

    public StoryItem(String title, String imageURL, long date) {
        this.title = title;
        this.imageURL = imageURL;
        this.date = date;
    }

    public StoryItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public long getDate() {
        return date;
    }
}
