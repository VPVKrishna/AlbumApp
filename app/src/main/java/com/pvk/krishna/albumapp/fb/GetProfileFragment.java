package com.pvk.krishna.albumapp.fb;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pvk.krishna.albumapp.R;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnProfileListener;

public class GetProfileFragment extends BaseFragment {

    private final static String EXAMPLE = "Get profile";

    private TextView mResult;
    private Button mGetButton;
    private TextView mMore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(EXAMPLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example_action, container, false);
        mResult = (TextView) view.findViewById(R.id.result);
        mMore = (TextView) view.findViewById(R.id.load_more);
        mMore.setPaintFlags(mMore.getPaint().getFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mGetButton = (Button) view.findViewById(R.id.button);
        mGetButton.setText(EXAMPLE);
        disableLoadMore();
        mGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // PictureAttributes pictureAttributes = Attributes.createPictureAttributes();
                // pictureAttributes.setHeight(500);
                // pictureAttributes.setWidth(500);
                // pictureAttributes.setType(PictureType.SQUARE);
                // Properties properties = new Properties.Builder()
                //		.add(Properties.PICTURE, pictureAttributes)
                //		.build();


            }
        });
        return view;
    }

    private void disableLoadMore() {
        mMore.setOnClickListener(null);
        mMore.setVisibility(View.INVISIBLE);
    }
}
