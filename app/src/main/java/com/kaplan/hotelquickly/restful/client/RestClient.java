package com.kaplan.hotelquickly.restful.client;


import com.google.gson.internal.LinkedTreeMap;
import com.kaplan.hotelquickly.util.NetworkConstants;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.androidannotations.api.rest.RestClientHeaders;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

@Rest(rootUrl = NetworkConstants.BASE_URL, converters = {GsonHttpMessageConverter.class, StringHttpMessageConverter.class})
public interface RestClient extends RestClientHeaders, RestClientErrorHandling {

    @Get(NetworkConstants.WEBVIEW_URLS)
    LinkedTreeMap getUrls();
}


