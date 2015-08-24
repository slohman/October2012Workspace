package com.yctc.alpaware;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import android.app.Application;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class AppSettings extends Application {
    private static final DefaultHttpClient client = createClient();

    @Override
    public void onCreate(){
    }

    static DefaultHttpClient getClient(){
            return client;
    }
    private static DefaultHttpClient createClient(){
            BasicHttpParams params = new BasicHttpParams();
            int timeoutConnection = 10000;
            HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);
            int timeoutSocket = 10000;
            HttpConnectionParams.setSoTimeout(params, timeoutSocket);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            final SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
            schemeRegistry.register(new Scheme("https", sslSocketFactory, 443));
            ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
            DefaultHttpClient httpclient = new DefaultHttpClient(cm, params);
            httpclient.getCookieStore().getCookies();
            return httpclient;
    }
}