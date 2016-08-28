package zadeye.recyclerviewheaderslidertest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class Main2Activity extends AppCompatActivity implements MainView {

    Context context;

    RecyclerView rvDemo;
    CategoryAdapter categoryAdapter;
    APIInteractor apiInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        context  = this;
        rvDemo = (RecyclerView) findViewById(R.id.rvDemo);
        rvDemo.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvDemo.setLayoutManager(llm);
        apiInteractor = new APIInteractorImpl(context);
        apiInteractor.getCategories(this);
    }

    @Override
    public void setAdapter(CategoryResponse categoryResponse) {
        categoryAdapter = new CategoryAdapter(categoryResponse, context,getSupportFragmentManager(), this);
        rvDemo.setAdapter(categoryAdapter);
    }

    @Override
    public void openProductActivity(Category category) {
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra(StaticData.CAT_ID, category.getCat_id());
        startActivity(intent);
    }
}
