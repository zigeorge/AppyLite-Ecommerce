package zadeye.recyclerviewheaderslidertest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements ProductView{

    Context context;
    String category_id;
    RecyclerView rvProducts;
    ProgressBar progressBar;
    APIInteractor apiInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        context = this;

        category_id = getIntent().getStringExtra(StaticData.CAT_ID);
        rvProducts = (RecyclerView) findViewById(R.id.rvProducts);
        progressBar = (ProgressBar) findViewById(R.id.pbProducts);
        progressBar.setVisibility(View.VISIBLE);
        rvProducts.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvProducts.setLayoutManager(llm);
        apiInteractor = new APIInteractorImpl(context);
        apiInteractor.getProducts(this);
    }

    @Override
    public void setUpProductsList(ProductsResponse productsResponse) {
        progressBar.setVisibility(View.GONE);
        ProductAdapter productAdapter = new ProductAdapter(productsResponse, context, getSupportFragmentManager(), this);
        rvProducts.setAdapter(productAdapter);
    }

    @Override
    public void openProductDetail(Product product) {
        Intent intent = new Intent(context,ProductDetailsActivity.class);
        intent.putExtra(StaticData.PRODUCT, product);
        startActivity(intent);
    }
}
