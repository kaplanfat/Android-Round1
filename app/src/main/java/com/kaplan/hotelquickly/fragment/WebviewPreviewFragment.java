package com.kaplan.hotelquickly.fragment;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kaplan.hotelquickly.R;
import com.kaplan.hotelquickly.util.AppConstants;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * Created by kaplanfatt on 15/01/16.
 */
@EFragment(R.layout.webview_preview_fragment)
public class WebviewPreviewFragment extends BaseFragment {

    @ViewById(R.id.webviewPreview)
    WebView webviewPreview;

    @FragmentArg
    boolean cache;

    @FragmentArg
    String link;

    @AfterViews
    protected void afterViews() {
        webviewPreview.getSettings().setAppCacheMaxSize(5 * 1024 * 1024); // 5MB
        webviewPreview.getSettings().setAppCachePath(getActivity().getApplicationContext().getCacheDir().getAbsolutePath());
        webviewPreview.getSettings().setAllowFileAccess(true);
        webviewPreview.getSettings().setAppCacheEnabled(true);
        webviewPreview.getSettings().setJavaScriptEnabled(true);

        if (cache) {
            webviewPreview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        } else {
            showHotelQuicklyProgress();
            webviewPreview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        }
        webviewPreview.loadUrl(convertLink(link));
        webviewPreview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                hideHotelQuicklyProgress();
            }
        });
    }


    public String convertLink(String link) {
        String linkConverted = "";
        link = link.replace("{userId}", AppConstants.userId);
        link = link.replace("{appSecretKey}", AppConstants.appSecretKey);
        link = link.replace("{currencyCode}", AppConstants.currencyCode);
        link = link.replace("{offerId}", AppConstants.offerId);
        link = link.replace("{selectedVouchers}", AppConstants.selectedVouchers);
        linkConverted = link;
        return linkConverted;
    }


}
