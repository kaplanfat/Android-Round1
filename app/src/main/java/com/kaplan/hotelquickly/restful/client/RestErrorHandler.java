package com.kaplan.hotelquickly.restful.client;

import org.androidannotations.annotations.EBean;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by Fatih Kaplan
 */

@EBean
public class RestErrorHandler implements org.androidannotations.api.rest.RestErrorHandler {


    public interface SendErrorListener {
        void sendError(String errorMessage);
    }

    SendErrorListener errorListener;

    public void setSendErrorListener(SendErrorListener l) {
        errorListener = l;
    }


    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException runtimeException) {
        System.out.println("backend error");
        if (runtimeException instanceof HttpClientErrorException) {
            HttpClientErrorException exception = (HttpClientErrorException) runtimeException;
            if (exception.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                errorListener.sendError(exception.getStatusText());
            }
        }

    }


}
