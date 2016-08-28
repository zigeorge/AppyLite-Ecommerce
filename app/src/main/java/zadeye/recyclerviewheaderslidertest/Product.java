package zadeye.recyclerviewheaderslidertest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ppobd_six on 26-August-2016.
 */
public class Product implements Parcelable {
    private String prod_id;
    private String prod_name;
    private int prod_price;
    private String prod_thumb;
    private String prod_background;
    private String prod_desc;
    private int prod_availability;
    private ArrayList<String> prod_attributes;

    protected Product(Parcel in) {
        prod_id = in.readString();
        prod_name = in.readString();
        prod_price = in.readInt();
        prod_thumb = in.readString();
        prod_background = in.readString();
        prod_desc = in.readString();
        prod_availability = in.readInt();
        prod_attributes = in.createStringArrayList();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public int getProd_price() {
        return prod_price;
    }

    public void setProd_price(int prod_price) {
        this.prod_price = prod_price;
    }

    public String getProd_thumb() {
        return prod_thumb;
    }

    public void setProd_thumb(String prod_thumb) {
        this.prod_thumb = prod_thumb;
    }

    public String getProd_background() {
        return prod_background;
    }

    public void setProd_background(String prod_background) {
        this.prod_background = prod_background;
    }

    public String getProd_desc() {
        return prod_desc;
    }

    public void setProd_desc(String prod_desc) {
        this.prod_desc = prod_desc;
    }

    public int getProd_availability() {
        return prod_availability;
    }

    public void setProd_availability(int prod_availability) {
        this.prod_availability = prod_availability;
    }

    public ArrayList<String> getProd_attributes() {
        return prod_attributes;
    }

    public void setProd_attributes(ArrayList<String> prod_attributes) {
        this.prod_attributes = prod_attributes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(prod_id);
        dest.writeString(prod_name);
        dest.writeInt(prod_price);
        dest.writeString(prod_thumb);
        dest.writeString(prod_background);
        dest.writeString(prod_desc);
        dest.writeInt(prod_availability);
        dest.writeStringList(prod_attributes);
    }
}
