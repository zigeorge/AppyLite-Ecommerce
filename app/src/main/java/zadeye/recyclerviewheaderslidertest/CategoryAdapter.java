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

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    ArrayList<Category> categories;
    ArrayList<Banner> banners;
    Context context;
    FragmentManager fragmentManager;
    MainView mainView;

    public CategoryAdapter(CategoryResponse categoryResponse, Context context, FragmentManager fragmentManager, MainView mainView){
        this.categories = categoryResponse.getCategories();
        this.banners = categoryResponse.getCat_banner();
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.mainView = mainView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.sliding_header, parent, false);
            return new HeaderViewHolder (v);
        } else if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.category_row_list, parent, false);
            return new CategoryViewHolder (v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Work from here on
        Category category = categories.get(position);
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
            final CategoryViewHolder categoryViewHolder = (CategoryViewHolder) holder;
            if(!TextUtils.isEmpty(category.getCat_background())){
                Picasso.with(context).load(category.getCat_background()).placeholder(R.drawable.progress_animation)
                        .into(categoryViewHolder.ivCategoryBackground, new Callback() {
                            @Override
                            public void onSuccess() {
                                categoryViewHolder.ivCategoryBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            }

                            @Override
                            public void onError() {

                            }
                        });
            }else {
                categoryViewHolder.ivCategoryBackground.setVisibility(View.GONE);
            }
            if(!TextUtils.isEmpty(category.getCat_thumb())){
                Picasso.with(context).load(category.getCat_thumb()).placeholder(R.drawable.progress_animation)
                        .into(categoryViewHolder.ivCategoryThumb, new Callback() {
                            @Override
                            public void onSuccess() {
                                categoryViewHolder.ivCategoryThumb.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            }

                            @Override
                            public void onError() {

                            }
                        });
            }else{
                categoryViewHolder.ivCategoryThumb.setVisibility(View.GONE);
            }
            categoryViewHolder.tvCategoryName.setText(category.getCat_name());
            categoryViewHolder.tvCatNoOfProds.setText("("+category.getCat_noofitems()+")");
            //set click-listener for cvContainer
            categoryViewHolder.cvContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainView.openProductActivity(categories.get(position));
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
        return categories.size();
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

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        protected ImageView ivCategoryBackground;
        protected ImageView ivCategoryThumb;
        protected TextView tvCategoryName;
        protected TextView tvCatNoOfProds;
        protected CardView cvContainer;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ivCategoryBackground = (ImageView) itemView.findViewById(R.id.ivCategoryBackground);
            ivCategoryThumb = (ImageView) itemView.findViewById(R.id.ivCategoryThumb);
            tvCategoryName = (TextView) itemView.findViewById(R.id.tvCategoryName);
            tvCatNoOfProds = (TextView) itemView.findViewById(R.id.tvCatNoOfProds);
            cvContainer = (CardView) itemView.findViewById(R.id.cvContainer);
        }
    }


}
