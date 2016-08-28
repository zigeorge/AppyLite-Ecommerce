package zadeye.recyclerviewheaderslidertest;

import java.util.ArrayList;

/**
 * Created by ppobd_six on 26-August-2016.
 */
public interface ProductView {
    void setUpProductsList(ProductsResponse productsResponse);

    void openProductDetail(Product product);
}
