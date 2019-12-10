package sjn.project.djcode;

public class Branch {
    private String img;
    private String name;
    private String address;
    private int price;

    public Branch() {

    }

    public Branch(String img, String name, String address, int price) {
        this.img = img;
        this.name = name;
        this.address = address;
        this.price = price;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
