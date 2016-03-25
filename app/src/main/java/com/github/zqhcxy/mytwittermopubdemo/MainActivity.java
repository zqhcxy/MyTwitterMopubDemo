package com.github.zqhcxy.mytwittermopubdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.zqhcxy.mytwittermopubdemo.activity.InterstitialActivity;
import com.github.zqhcxy.mytwittermopubdemo.common.MopubAdUtil;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;

public class MainActivity extends AppCompatActivity implements MoPubView.BannerAdListener, View.OnClickListener {


    private static final int BANNER = 0;
    private static final int MRECT = 1;
    private static final String TAG = "zqh-mopub";

    private TextView title_banner;
    private TextView title_mrect;
    private Button ad_mopub_interstitial;

    private MoPubView ad_mopub_banner;//banner广告
    private MoPubView ad_mopub_mrect;//Mrect 广告

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
    }

    private void findView() {
        title_banner = (TextView) findViewById(R.id.title_banner);
        title_mrect = (TextView) findViewById(R.id.title_mrect);
        ad_mopub_interstitial = (Button) findViewById(R.id.ad_mopub_interstitial);

        ad_mopub_banner = (MoPubView) findViewById(R.id.ad_mopub_banner);
        ad_mopub_mrect = (MoPubView) findViewById(R.id.ad_mopub_mrect);
        loadMoPubView(BANNER, MopubAdUtil.BINNER_KEY, null);
        ad_mopub_banner.setBannerAdListener(this);
        ad_mopub_mrect.setBannerAdListener(this);
        title_banner.setOnClickListener(this);
        title_mrect.setOnClickListener(this);
        ad_mopub_interstitial.setOnClickListener(this);
    }

    private void loadMoPubView(int type, final String adUnitId, final String keywords) {
        switch (type) {
            case BANNER:
                ad_mopub_banner.setAdUnitId(adUnitId);
                ad_mopub_banner.loadAd();
                break;
            case MRECT:
                ad_mopub_mrect.setAdUnitId(adUnitId);
                ad_mopub_mrect.loadAd();
                break;
        }

    }


    //Sent when the banner has successfully retrieved an ad.
    @Override
    public void onBannerLoaded(MoPubView banner) {
        Log.i(TAG, "Banner successfully loaded.");
//        Toast.makeText(getApplicationContext(),
//                "Banner successfully loaded.", Toast.LENGTH_SHORT).show();
    }

    // Sent when the banner has failed to retrieve an ad.
    // You can use the MoPubErrorCode value to diagnose the cause of failure.
    @Override
    public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
        final String errorMessage = (errorCode != null) ? errorCode.toString() : "";
        Log.i(TAG, "Banner Failed：" + errorMessage);
//        Toast.makeText(getApplicationContext(),
//                "Banner Failed.", Toast.LENGTH_SHORT).show();

    }

    // Sent when the user has tapped on the banner.
    @Override
    public void onBannerClicked(MoPubView banner) {
        Log.i(TAG, "Banner onBannerClicked.");
    }

    // Sent when the banner has just taken over the screen.
    @Override
    public void onBannerExpanded(MoPubView banner) {
        Log.i(TAG, "Banner onBannerExpanded.");
    }

    // Sent when an expanded banner has collapsed back to its original size.
    @Override
    public void onBannerCollapsed(MoPubView banner) {
        Log.i(TAG, "Banner onBannerCollapsed.");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_banner:
                loadMoPubView(BANNER, MopubAdUtil.BINNER_KEY, null);
                break;

            case R.id.title_mrect:
                loadMoPubView(MRECT, MopubAdUtil.MRECT_KEY, null);
                break;
            case R.id.ad_mopub_interstitial:
                Intent intent = new Intent(MainActivity.this, InterstitialActivity.class);
                startActivity(intent);
                break;

        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ad_mopub_banner != null) {
            ad_mopub_banner.destroy();
            ad_mopub_banner = null;
        }
        if (ad_mopub_mrect != null) {
            ad_mopub_mrect.destroy();
            ad_mopub_mrect = null;
        }
    }
}
