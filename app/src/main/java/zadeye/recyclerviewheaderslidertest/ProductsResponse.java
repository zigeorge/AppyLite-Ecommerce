package zadeye.recyclerviewheaderslidertest;

import java.util.ArrayList;

/**
 * Created by ppobd_six on 27-August-2016.
 */
public class ProductsResponse {
    private ArrayList<Product> products;
    private ArrayList<Banner> prod_banners;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Banner> getProd_banners() {
        return prod_banners;
    }

    public void setProd_banners(ArrayList<Banner> prod_banners) {
        this.prod_banners = prod_banners;
    }
}
