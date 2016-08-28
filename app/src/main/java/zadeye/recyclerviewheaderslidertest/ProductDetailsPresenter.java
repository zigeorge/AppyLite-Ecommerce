package zadeye.recyclerviewheaderslidertest;

/**
 * Created by ppobd_six on 28-August-2016.
 */
public interface ProductDetailsPresenter {
    void calculateQuantity(boolean isIncreased, int availability, int current, int total, int price);
}
