package sjn.project.djcode;

public class Themes {
    private int themeImg;
    private String themeName;
    private String difficult;
    private String category;
    private String person;

    public Themes(int themeImg, String themeName, String difficult, String category, String person) {
        this.themeImg = themeImg;
        this.themeName = themeName;
        this.difficult = difficult;
        this.category = category;
        this.person = person;
    }

    public int getThemeImg() {
        return themeImg;
    }

    public void setThemeImg(int themeImg) {
        this.themeImg = themeImg;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
