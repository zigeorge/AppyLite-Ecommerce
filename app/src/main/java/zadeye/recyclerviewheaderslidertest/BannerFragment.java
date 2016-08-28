package zadeye.recyclerviewheaderslidertest;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class BannerFragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
    private String imageUrl;

    ImageView ivBannerImage;
    Context context;

    public BannerFragment() {
        // Required empty public constructor
    }

    public static BannerFragment newInstance(String imageUrl) {
        BannerFragment fragment = new BannerFragment();
        Bundle args = new Bundle();
        args.putString(StaticData.IMAGE_URL, imageUrl);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageUrl = getArguments().getString(StaticData.IMAGE_URL);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_banner, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        initUI(view);
    }

    private void initUI(View view){
        ivBannerImage = (ImageView) view.findViewById(R.id.ivBannerImage);
        Picasso.with(context).load(imageUrl).placeholder(R.drawable.progress_animation).error(R.drawable.sample_image).into(ivBannerImage, new Callback() {
            @Override
            public void onSuccess() {
                ivBannerImage.setScaleType(ImageView.ScaleType.FIT_XY);
            }

            @Override
            public void onError() {

            }
        });
    }
}
