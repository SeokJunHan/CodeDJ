package sjn.project.djcode;

public class Theme {
    private String img;
    private String name;
    private String genre;
    private int difficulty;
    private String num_people;

    public Theme(String img, String name, String genre, int difficulty, String num_people) {
        this.img = img;
        this.name = name;
        this.genre = genre;
        this.difficulty = difficulty;
        this.num_people = num_people;
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getNum_people() {
        return num_people;
    }

    public void setNum_people(String num_people) {
        this.num_people = num_people;
    }
}
