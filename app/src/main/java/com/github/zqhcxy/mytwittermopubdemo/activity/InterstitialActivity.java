package com.github.zqhcxy.mytwittermopubdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.zqhcxy.mytwittermopubdemo.R;
import com.github.zqhcxy.mytwittermopubdemo.common.MopubAdUtil;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

/**
 * 插播广告
 */
public class InterstitialActivity extends AppCompatActivity implements MoPubInterstitial.InterstitialAdListener {

    private MoPubInterstitial moPubInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);

        moPubInterstitial = new MoPubInterstitial(this, MopubAdUtil.MRECT_KEY);
        moPubInterstitial.setInterstitialAdListener(this);
        moPubInterstitial.load();
    }

    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        if (interstitial.isReady()) {
            interstitial.show();
            Log.i("zqh", "successed");
        } else {
            Log.i("zqh", "loaded");
        }

    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
        final String errorMessage = (errorCode != null) ? errorCode.toString() : "";
        Log.i("zqh", errorMessage);
    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {

    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {

    }

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {

    }


    @Override
    protected void onDestroy() {
        if (moPubInterstitial != null) {
            moPubInterstitial.destroy();
            moPubInterstitial = null;
        }
        super.onDestroy();
    }
}
