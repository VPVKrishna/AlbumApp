package com.pvk.krishna.albumapp.activity;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by Krishna on 13/06/2015.
 */
public class LoadingActivity extends Activity {

    private ProgressDialog mProgressDialog;

    protected void showDialog() {
        if (mProgressDialog == null) {
            setProgressDialog();
        }
        mProgressDialog.show();
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private void setProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Thinking...");
        mProgressDialog.setMessage("Doing the action...");
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

}
