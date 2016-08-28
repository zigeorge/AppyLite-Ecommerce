package zadeye.recyclerviewheaderslidertest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity implements ProductDetailsView {

    Context context;

    ImageView ivProductImage, ivAddToCart, ivDecrease, ivIncrease;
    TextView tvProductName, tvProductPrice, tvProductDesc, tvQuantity, tvTotalPrice;
    Spinner spinnerAttribute;
    Product product;
    ProductDetailsPresenter productDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        context = this;
        product = getIntent().getParcelableExtra(StaticData.PRODUCT);
        productDetailsPresenter = new ProductDetailsPresenterImpl(context, this);
        initUI();
        setListeners();
        setData();
    }

    private void initUI() {
        ivProductImage = (ImageView) findViewById(R.id.ivProductImage);
        ivAddToCart = (ImageView) findViewById(R.id.ivAddToCart);
        ivDecrease = (ImageView) findViewById(R.id.ivDecrease);
        ivIncrease = (ImageView) findViewById(R.id.ivIncrease);
        tvProductName = (TextView) findViewById(R.id.tvProductName);
        tvProductPrice = (TextView) findViewById(R.id.tvProductPrice);
        tvProductDesc = (TextView) findViewById(R.id.tvProductDesc);
        tvQuantity = (TextView) findViewById(R.id.tvQuantity);
        tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);
        spinnerAttribute = (Spinner) findViewById(R.id.spinnerAttribute);
    }

    private void setListeners() {
        ivIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = Integer.parseInt(tvQuantity.getText().toString());
                int total = Integer.parseInt(tvTotalPrice.getText().toString());
                productDetailsPresenter.calculateQuantity(true, product.getProd_availability(), current, total, product.getProd_price());
            }
        });
        ivDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = Integer.parseInt(tvQuantity.getText().toString());
                int total = Integer.parseInt(tvTotalPrice.getText().toString());
                productDetailsPresenter.calculateQuantity(false, product.getProd_availability(), current, total, product.getProd_price());
            }
        });
    }

    private void setData() {
        Picasso.with(context).load(product.getProd_thumb()).placeholder(R.drawable.progress_animation).into(ivProductImage);
        tvProductName.setText(product.getProd_name());
        tvProductPrice.setText(product.getProd_price() + " BDT");
        tvProductDesc.setText(product.getProd_desc());
        tvQuantity.setText("01");
        tvTotalPrice.setText(product.getProd_price() + "");
        setSpinner();
    }

    private void setSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, product.getProd_attributes());
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAttribute.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateTotalAndQuantity(int quantity, int total) {
        if (quantity < 10) {
            tvQuantity.setText("0" + quantity);
        } else {
            tvQuantity.setText(quantity + "");
        }
        tvTotalPrice.setText(total + "");
    }
}
