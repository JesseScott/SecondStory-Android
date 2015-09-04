
package com.theonlyanimal.secondstory.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theonlyanimal.secondstory.R;


public class AboutFragment extends Fragment {

    public static final String ARG_PAGE = "page";
    private int mPageNumber;

    public static AboutFragment create(int pageNumber) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public AboutFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.about_page_layout, container, false);

        ((TextView) rootView.findViewById(R.id.about_page_title)).setText("Title: " + getResources().getStringArray(R.array.vid_title)[mPageNumber]);
        ((TextView) rootView.findViewById(R.id.about_page_director)).setText("Director: " + getResources().getStringArray(R.array.vid_directors)[mPageNumber]);


        return rootView;
    }

    public int getPageNumber() {
        return mPageNumber;
    }




} /* eoc */


