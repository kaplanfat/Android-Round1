package com.kaplan.hotelquickly.interfaces;


import android.os.Bundle;

public interface OnMainFragmentListener {


    void onCloseFragment(String tag);

    void onStartFragment(String tag);

    void sendDataFromFragment(Bundle bundle);

    void loginSuccess(boolean isSuccess);

    void backClicked(String fragmentName);

}
