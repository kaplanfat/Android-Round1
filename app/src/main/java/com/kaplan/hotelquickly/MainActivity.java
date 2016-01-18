package com.kaplan.hotelquickly;

import com.kaplan.hotelquickly.activity.BaseActivity;
import com.kaplan.hotelquickly.fragment.WebviewListFragment;
import com.kaplan.hotelquickly.fragment.WebviewListFragment_;
import com.kaplan.hotelquickly.fragment.WebviewPreviewFragment;
import com.kaplan.hotelquickly.fragment.WebviewPreviewFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @AfterViews
    protected void afterViews() {
        toWebViewList();
    }

    public void toWebViewList() {
        WebviewListFragment webviewListFragment = new WebviewListFragment_();
        replaceFragment(R.id.main_frame, webviewListFragment, true);
    }

    public void toWebViewDetail(String link, boolean cache) {
        WebviewPreviewFragment webviewPreviewFragment = WebviewPreviewFragment_.builder().cache(cache).link(link).build();
        replaceFragmentWithAnimation(R.id.main_frame, webviewPreviewFragment, true);
    }


}
