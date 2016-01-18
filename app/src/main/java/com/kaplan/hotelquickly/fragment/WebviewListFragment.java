package com.kaplan.hotelquickly.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.kaplan.hotelquickly.MainActivity;
import com.kaplan.hotelquickly.R;
import com.kaplan.hotelquickly.restful.client.RestClient;
import com.kaplan.hotelquickly.restful.model.WebviewDataModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kaplanfatt on 15/01/16.
 */
@EFragment(R.layout.webviewlist_fragment)
public class WebviewListFragment extends BaseFragment {

    @RestService
    RestClient restClient;

    @ViewById(R.id.webviewList)
    ListView webviewList;

    LinkedTreeMap linkedTreeMap;

    ArrayList<WebviewDataModel> webviewDataModels;


    @AfterViews
    protected void afterViews() {
        getList();
    }

    @Background
    void getList() {

        linkedTreeMap = restClient.getUrls();
        try {
            JSONObject jsonObject = new JSONObject(linkedTreeMap);
            if (jsonObject != null) {
                webviewDataModels = new ArrayList<WebviewDataModel>();
                Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    WebviewDataModel webviewDataModel = new WebviewDataModel();
                    String currentDynamicKey = (String) keys.next();
                    Gson gson = new Gson();
                    JSONObject jsonObjectDataModel = jsonObject.getJSONObject(currentDynamicKey);
                    webviewDataModel = gson.fromJson(jsonObjectDataModel.toString(), WebviewDataModel.class);
                    webviewDataModel.webViewTitle = currentDynamicKey;
                    webviewDataModels.add(webviewDataModel);
                }
                if (webviewDataModels.size() > 0)
                    initUI(webviewDataModels);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @ItemClick(R.id.webviewList)
    void listItemClick(int position) {
        WebviewDataModel webviewDataModel = webviewDataModels.get(position);
        if (webviewDataModel != null) {
            String link = webviewDataModel.url;
            boolean cache = webviewDataModel.cache;
            ((MainActivity) getActivity()).toWebViewDetail(link, cache);
        }
    }

    @UiThread
    void initUI(ArrayList<WebviewDataModel> webviewDataModels) {
        WebviewListAdapter adapter = new WebviewListAdapter(getActivity(), webviewDataModels);
        webviewList.setAdapter(adapter);
    }

    private class WebviewListAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private ArrayList<WebviewDataModel> webviewList;


        public WebviewListAdapter(Context context, ArrayList<WebviewDataModel> dataModels) {
            webviewList = dataModels;
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return webviewList.size();
        }

        @Override
        public WebviewDataModel getItem(int position) {
            return webviewList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            View vi = view;
            ViewHolder holder = null;
            if (vi == null) {
                vi = inflater.inflate(R.layout.webview_list_row, parent, false);
                holder = new ViewHolder();
                holder.name = (TextView) vi.findViewById(R.id.webViewListItemTextView);
                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }
            final WebviewDataModel dataModel = getItem(position);

            if (dataModel != null) {
                holder.name.setText(dataModel.webViewTitle);
            }
            return vi;
        }
    }

    static class ViewHolder {
        TextView name;
    }
}
