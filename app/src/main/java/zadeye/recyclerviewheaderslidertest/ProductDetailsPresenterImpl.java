package zadeye.recyclerviewheaderslidertest;

import android.content.Context;

/**
 * Created by ppobd_six on 28-August-2016.
 */
public class ProductDetailsPresenterImpl implements ProductDetailsPresenter {

    Context context;
    ProductDetailsView productDetailsView;

    public ProductDetailsPresenterImpl(Context context, ProductDetailsView productDetailsView) {
        this.context = context;
        this.productDetailsView = productDetailsView;
    }

    @Override
    public void calculateQuantity(boolean isIncreased, int availability, int current, int total, int price) {
        if(isIncreased){
            if(current==availability){
                productDetailsView.showMessage("Reached the available quantity");
            }else{
                current++;
                total+=price;
                productDetailsView.updateTotalAndQuantity(current, total);
            }
        }else{
            if(current==1){
                productDetailsView.showMessage("Minimum order amount is 1");
            }else{
                current--;
                total-=price;
                productDetailsView.updateTotalAndQuantity(current, total);
            }
        }
    }
}
