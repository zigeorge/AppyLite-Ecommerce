package zadeye.recyclerviewheaderslidertest;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ppobd_six on 25-August-2016.
 */
public class APIInteractorImpl implements APIInteractor {

    Context context;
    Retrofit retrofit;
    APIServiceInterface apiServiceInterface;

    public APIInteractorImpl(Context context){
        this.context = context;
        initAPIService();
    }

    private void initAPIService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(StaticData.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServiceInterface = retrofit.create(APIServiceInterface.class);
    }


    @Override
    public void getCategories(final MainView mainView) {
        Call<CategoryResponse> call = apiServiceInterface.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()) {
                    mainView.setAdapter(response.body());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getProducts(final ProductView productView) {
        Call<ProductsResponse> call = apiServiceInterface.getProducts();
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if(response.isSuccessful()){
                    productView.setUpProductsList(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {

            }
        });
    }
}
