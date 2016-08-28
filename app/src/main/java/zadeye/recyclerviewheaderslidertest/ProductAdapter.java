package zadeye.recyclerviewheaderslidertest;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    ArrayList<Product> products;
    ArrayList<Banner> banners;
    Context context;
    FragmentManager fragmentManager;
    ProductView productView;

    public ProductAdapter(ProductsResponse productsResponse, Context context, FragmentManager fragmentManager, ProductView productView){
        this.products = productsResponse.getProducts();
        this.banners = productsResponse.getProd_banners();
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.productView = productView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.sliding_header, parent, false);
            return new HeaderViewHolder (v);
        } else if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.product_row_list, parent, false);
            return new ProductViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Work from here on
        Product product = products.get(position);
        if(holder instanceof HeaderViewHolder){
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            ArrayList<BannerFragment> bannerFragments = new ArrayList<>();
            for (Banner banner:
                 banners) {
                BannerFragment bannerFragment = BannerFragment.newInstance(banner.getBanner_image());
                bannerFragments.add(bannerFragment);
            }
            BannerPagerAdapter bannerPagerAdapter = new BannerPagerAdapter(fragmentManager,bannerFragments);
            headerHolder.vpBanner.setAdapter(bannerPagerAdapter);
            headerHolder.cpiBanner.setViewPager(headerHolder.vpBanner);
            headerHolder.vpBanner.startAutoScroll();
//            startSlider(headerHolder.vpBanner);
        }else{
            final ProductViewHolder productViewHolder = (ProductViewHolder) holder;
            if(!TextUtils.isEmpty(product.getProd_background())){
                Picasso.with(context).load(product.getProd_background()).placeholder(R.drawable.progress_animation)
                        .into(productViewHolder.ivProductBackground, new Callback() {
                            @Override
                            public void onSuccess() {
                                productViewHolder.ivProductBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            }

                            @Override
                            public void onError() {

                            }
                        });
            }else {
                productViewHolder.ivProductBackground.setVisibility(View.GONE);
            }
            if(!TextUtils.isEmpty(product.getProd_thumb())){
                Picasso.with(context).load(product.getProd_thumb()).placeholder(R.drawable.progress_animation)
                        .into(productViewHolder.ivProductThumb, new Callback() {
                            @Override
                            public void onSuccess() {
                                productViewHolder.ivProductThumb.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            }

                            @Override
                            public void onError() {

                            }
                        });
            }else{
                productViewHolder.ivProductThumb.setVisibility(View.GONE);
            }
            productViewHolder.tvProductName.setText(product.getProd_name());
            productViewHolder.tvProductPrice.setText(product.getProd_price()+" BDT");
            //set click-listener for cvContainer
            productViewHolder.cvContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productView.openProductDetail(products.get(position));
                }
            });
        }

    }

//    private void startSlider(final ViewPager vpBanner){
//        final Handler handler = new Handler();
//        final Runnable update = new Runnable() {
//            public void run() {
//                int currentPage = vpBanner.getCurrentItem();
//                if (currentPage == banners.size() - 1) {
//                    currentPage = 0;
//                }
//                vpBanner.setCurrentItem(currentPage++, true);
//            }
//        };
//
//        handler.postDelayed(update,1000);
//        new Timer().schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                handler.post(update);
//            }
//        }, 400, 500);
//    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        AutoScrollViewPager vpBanner;
        CirclePageIndicator cpiBanner;

        public HeaderViewHolder (View itemView) {
            super (itemView);
            this.vpBanner = (AutoScrollViewPager) itemView.findViewById (R.id.vpBanner);
            this.cpiBanner = (CirclePageIndicator) itemView.findViewById(R.id.cpiBanner);
        }
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        protected ImageView ivProductBackground;
        protected ImageView ivProductThumb;
        protected TextView tvProductName;
        protected TextView tvProductPrice;
        protected CardView cvContainer;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ivProductBackground = (ImageView) itemView.findViewById(R.id.ivProductBackground);
            ivProductThumb = (ImageView) itemView.findViewById(R.id.ivProductThumb);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            cvContainer = (CardView) itemView.findViewById(R.id.cvContainer);
        }
    }


}
