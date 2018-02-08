package com.qa.utils;

import org.apache.http.Header;

import java.util.Iterator;
import java.util.Map.Entry;

public interface HttpRequestCallback {
	String getJsonParam();
    String getParam();
    Iterator<Entry<String, String>> getHeaderParameters();
    void saveResponseHeaders(Header[] responseHeaders);

    boolean getIsRedirect();
}
