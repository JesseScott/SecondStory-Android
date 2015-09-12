package com.theonlyanimal.secondstory.fragments;



import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.theonlyanimal.secondstory.R;


public class GalleryFragment extends Fragment {

    public static final String ARG_PAGE = "page";
    private static OnMovieSelectedListener mListener;
    private int mPageNumber;
    Typeface dinBlack, dinMedium;

    public static GalleryFragment create(int pageNumber) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public GalleryFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.gallery_page_layout, container, false);

        // Fonts
        dinBlack = Typeface.createFromAsset(getActivity().getAssets(), "fonts/din alternate black.ttf");
        dinMedium = Typeface.createFromAsset(getActivity().getAssets(), "fonts/din alternate medium.ttf");

        // TextViews
        TextView title = (TextView) rootView.findViewById(R.id.gallery_page_title);
        title.setText(getResources().getStringArray(R.array.vid_title)[mPageNumber]);
        title.setTypeface(dinMedium);

        TextView director = (TextView) rootView.findViewById(R.id.gallery_page_director);
        director.setText(getResources().getStringArray(R.array.vid_directors)[mPageNumber]);
        director.setTypeface(dinMedium);

        // Image
        TypedArray imgs = getResources().obtainTypedArray(R.array.vid_thumbs);

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        BitmapDrawable bmap = (BitmapDrawable) this.getResources().getDrawable(imgs.getResourceId(mPageNumber, -1));
        float bmapWidth = bmap.getBitmap().getWidth();
        float bmapHeight = bmap.getBitmap().getHeight();

        float wRatio = width / bmapWidth;
        float hRatio = height / bmapHeight;

        float ratioMultiplier = wRatio;
        if (hRatio < wRatio) {
            ratioMultiplier = hRatio;
        }

        int newBmapWidth = (int) (bmapWidth*ratioMultiplier);
        int newBmapHeight = (int) (bmapHeight*ratioMultiplier);

        ImageView iView = (ImageView) rootView.findViewById(R.id.gallery_page_bg);
        iView.setLayoutParams(new RelativeLayout.LayoutParams(newBmapWidth, newBmapHeight));

        ((ImageView) rootView.findViewById(R.id.gallery_page_bg)).setImageResource(imgs.getResourceId(mPageNumber, -1));

        // Button
        ImageButton play = (ImageButton) rootView.findViewById(R.id.gallery_page_btn);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMovieSelected(mPageNumber);
            }
        });

        return rootView;
    }

    public int getPageNumber() {
        return mPageNumber;
    }

    public interface OnMovieSelectedListener {
        public void onMovieSelected(int index);
    }

    public void setListener(OnMovieSelectedListener listener) {
        mListener = listener;
    }


} /* eoc */


