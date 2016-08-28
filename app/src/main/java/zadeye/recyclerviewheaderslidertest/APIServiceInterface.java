package zadeye.recyclerviewheaderslidertest;


import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServiceInterface {

    @GET("categories.json")
    Call<CategoryResponse> getCategories();

    @GET("products.json")
    Call<ProductsResponse> getProducts();
}
