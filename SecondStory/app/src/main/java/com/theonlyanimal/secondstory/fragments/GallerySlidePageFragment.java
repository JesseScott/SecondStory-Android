package com.theonlyanimal.secondstory.fragments;


import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.theonlyanimal.secondstory.R;


public class GallerySlidePageFragment extends Fragment {

    public static final String ARG_PAGE = "page";
    private int mPageNumber;

    public static GallerySlidePageFragment create(int pageNumber) {
        GallerySlidePageFragment fragment = new GallerySlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public GallerySlidePageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.gallery_page_layout, container, false);

        ((TextView) rootView.findViewById(R.id.gallery_page_title)).setText(getResources().getStringArray(R.array.vid_title)[mPageNumber]);
        ((TextView) rootView.findViewById(R.id.gallery_page_director)).setText(getResources().getStringArray(R.array.vid_directors)[mPageNumber]);

        TypedArray imgs = getResources().obtainTypedArray(R.array.vid_thumbs);
        ((ImageView)rootView.findViewById(R.id.gallery_page_bg)).setImageResource(imgs.getResourceId(mPageNumber, -1));

        return rootView;
    }

    public int getPageNumber() {
        return mPageNumber;
    }
}
