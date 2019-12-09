package sjn.project.djcode;

public class Points {
    private int pointImg;
    private String pointName;
    private String address;
    private String pointPrice;

    public Points(int pointImg, String pointName, String address, String pointPrice) {
        this.pointImg = pointImg;
        this.pointName = pointName;
        this.address = address;
        this.pointPrice = pointPrice;
    }

    public int getPointImg() {
        return pointImg;
    }

    public void setPointImg(int pointImg) {
        this.pointImg = pointImg;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(String pointPrice) {
        this.pointPrice = pointPrice;
    }
}
