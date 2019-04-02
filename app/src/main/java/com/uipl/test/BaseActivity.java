package com.uipl.test;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public abstract class BaseActivity extends AppCompatActivity {
    private TextView mTextViewScreenTitle;
    private ImageButton mImageButtonBack;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminate(true);
    }
    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = LayoutInflater.from(this);
        CoordinatorLayout coordinatorLayout= (CoordinatorLayout) inflater.inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = coordinatorLayout.findViewById(R.id.layout_container);
        mTextViewScreenTitle = coordinatorLayout.findViewById(R.id.text_screen_title);
        mImageButtonBack = coordinatorLayout.findViewById(R.id.image_back_button);

        inflater.inflate(layoutResID, activityContainer, true);

        super.setContentView(coordinatorLayout);
    }


    public void setScreenTitle(String title) {
        mTextViewScreenTitle.setText(title);
    }

    public ImageButton getBackButton() {
        return mImageButtonBack;
    }

    public void showProgressDialog() {
        if(!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
