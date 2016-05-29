package ne.com.hypergaragesale;


import java.net.URI;

public class Data {
    public String title;
    public String description;
    public String price;
    public int id;
    public String imgUri;
    public String address;
    public String lat;
    public String lon;


    Data(String title, String description,String price, int id,String imgUri, String address,String lat,String lon) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.id = id;
        this.imgUri=imgUri;
        this.address = address;
        this.lat= lat;
        this.lon=lon;
    }

    public String getImgUri() {
        return imgUri;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}
