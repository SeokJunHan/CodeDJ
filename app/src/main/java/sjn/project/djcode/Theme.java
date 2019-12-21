package sjn.project.djcode;

public class Theme {
    private String img;
    private String name;
    private String genre;
    private float difficulty;
    private String desc;

    public Theme() {

    }

    public Theme(String img, String name, String genre, float difficulty, String desc) {
        this.img = img;
        this.name = name;
        this.genre = genre;
        this.difficulty = difficulty;
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
