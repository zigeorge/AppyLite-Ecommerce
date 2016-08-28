package zadeye.recyclerviewheaderslidertest;

import java.util.ArrayList;

/**
 * Created by ppobd_six on 25-August-2016.
 */
public class CategoryResponse {
    private ArrayList<Category> categories;
    private ArrayList<Banner> cat_banner;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Banner> getCat_banner() {
        return cat_banner;
    }

    public void setCat_banner(ArrayList<Banner> cat_banner) {
        this.cat_banner = cat_banner;
    }
}
