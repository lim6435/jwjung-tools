/*
 * Copyright (c) 2018.
 * Made by jjwonyop
 */

package kr.co.jwjung.tools.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import sun.misc.BASE64Decoder;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class HttpClientWrapper {
    private SSLContext sslcontext;
    private SSLConnectionSocketFactory sslConnectionSocketFactory;
    private PoolingHttpClientConnectionManager cm;


    public HttpClientWrapper() {
        settingSSL();
    }

    private void settingSSL() {
        /////////////////
        // Create SSL Client
        /////////////////
        sslcontext = SSLContexts.createSystemDefault();
        sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                sslcontext, new String[]{"TLSv1", "SSLv3"}, null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", sslConnectionSocketFactory)
                .build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
    }

    public String sendPostStringByJson(String targetUrl, String message) throws IOException {
        String resStr = "";

        /////////////////
        // Create SSL Client
        /////////////////
        CloseableHttpClient httpclient = null;
        HttpHost target = new HttpHost(targetUrl, 443, "https");

        httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setConnectionManager(cm)
                .build();

        /////////////////
        // Send POST
        /////////////////

        HttpPost httppost = new HttpPost(targetUrl);
        httppost.addHeader("content-type", "application/json");
        httppost.addHeader("Accept", "application/json");
        StringEntity params = new StringEntity(URLEncoder.encode(message, "UTF-8"));


        httppost.setEntity(params);

        /////////////////
        // Get RESPONSE
        /////////////////
        HttpResponse response = null;
        StringBuffer sb = new StringBuffer();
        try {
            response = httpclient.execute(httppost);
            System.out.println("STATUS CODE ::: " + response.getStatusLine().getStatusCode());
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            while ((resStr = reader.readLine()) != null) {
                sb.append(resStr);
            }
            resStr = sb.toString();
            System.out.println(resStr);
            reader.close();
        } finally {
            cm.close();
        }
        return resStr;
    }

    public String sendPostQryStrBase64(String targetUrl, HashMap<String, String> reqMap) throws Exception{
        String res = "";

        CloseableHttpClient httpclient = null;
        HttpHost target = new HttpHost(targetUrl, 443, "https");

        httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setConnectionManager(cm)
                .build();
        HttpPost post = new HttpPost(targetUrl);

        List<NameValuePair> arguments = new ArrayList<NameValuePair>();
        NameValuePair[] valuePairs = this.map2Param(reqMap);

        for(int i= 0; i<valuePairs.length; i++) {
            arguments.add(valuePairs[i]);
        }
        System.out.println(arguments.toString());
        post.setEntity(new StringEntity(arguments.toString()));

        System.out.println("executing Request ::: " + post.getRequestLine());

        HttpResponse response = httpclient.execute(post);
        HttpEntity respEntity = response.getEntity();
        System.out.println("Response Status ::: " + response.getStatusLine().getStatusCode());
        if(respEntity != null) {
            res = this.getResponseMessage(respEntity.getContent());
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bArr = decoder.decodeBuffer(res);
            System.out.println(new String(bArr, "UTF-8"));
        }

        return res;
    }

    public String sendPostQryStr(String targetUrl, HashMap<String, String> reqMap) throws Exception {
        String res = "";

        CloseableHttpClient httpclient = null;
        HttpHost target = new HttpHost(targetUrl, 443, "https");

        httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setConnectionManager(cm)
                .build();
        HttpPost post = new HttpPost(targetUrl);

        List<NameValuePair> arguments = new ArrayList<NameValuePair>();
        NameValuePair[] valuePairs = this.map2Param(reqMap);

        for(int i= 0; i<valuePairs.length; i++) {
            arguments.add(valuePairs[i]);
        }

        post.setEntity(new UrlEncodedFormEntity(arguments));

        System.out.println("executing Request ::: " + post.getRequestLine());

        HttpResponse response = httpclient.execute(post);
        HttpEntity respEntity = response.getEntity();
        System.out.println("Response Status ::: " + response.getStatusLine().getStatusCode());
        if(respEntity != null) {
            res = this.getResponseMessage(respEntity.getContent());
            System.out.println(res);
        }

        return res;
    }

    private String getResponseMessage(InputStream is) {
        StringBuffer str = new StringBuffer();

        String line = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while((line = reader.readLine()) != null) {
                str.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    private NameValuePair[] map2Param(HashMap<String, String> paramMap) {
        if (paramMap == null || paramMap.isEmpty()) {
            return null;
        }

        NameValuePair[] valuePairs = new NameValuePair[paramMap.size()];
        int i = 0;
        Iterator<String> it = paramMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = paramMap.get(key);
            valuePairs[i] = new BasicNameValuePair(key, value);
            i++;
        }
        return valuePairs;
    }
}
