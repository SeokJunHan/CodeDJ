package sjn.project.djcode.value_objects;

public class Review {
    private String id;
    private String theme;
    private String title;
    private int rate;
    private int difficulty;
    private int scary;
    private int activity;
    private String content;

    public Review() {

    }

    public Review(String id, String theme, String title, int rate, int difficulty, int scary, int activity, String content) {
        this.id = id;
        this.theme = theme;
        this.title = title;
        this.rate = rate;
        this.difficulty = difficulty;
        this.scary = scary;
        this.activity = activity;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getScary() {
        return scary;
    }

    public void setScary(int scary) {
        this.scary = scary;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
