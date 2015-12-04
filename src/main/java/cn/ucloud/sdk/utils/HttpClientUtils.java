// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package cn.ucloud.sdk.utils;

import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.log4j.Logger;

import cn.ucloud.sdk.enums.MsgEnum;

/**
 * 
 * @author Jack shen<37393993@qq.com>
 * 
 */
public class HttpClientUtils {
    private static final Logger logger = Logger.getLogger(HttpClientUtils.class);
    private static int CONNECTION_TIMEOUT = 60000;
    private static int SO_TIMEOUT = 0;
    
    private static X509TrustManager tm = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    @SuppressWarnings("deprecation")
    private static HttpClient getInstance() {
        HttpClient client = new DefaultHttpClient();
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[] { tm }, null);
        } catch (Exception e) {
            LogUtils.exception(logger, e);
        }
        SSLSocketFactory ssf = new SSLSocketFactory(ctx);
        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = client.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", ssf, 443));
        client = new DefaultHttpClient(ccm, client.getParams());
        return client;
    }

    
    public static HttpResponse putByHttps(String url, Map<String, String> headers, InputStream instream, Integer length) {
        HttpClient httpclient = getInstance();
        return put(url, httpclient, headers, instream, length);
    }

    public static HttpResponse postByHttps(String url, Map<String, String> header) {
        HttpClient httpclient = getInstance();

        return post(url, httpclient, header);
    }

    public static HttpResponse headByHttps(String url, Map<String, String> header) {
        HttpClient httpclient = getInstance();

        return head(url, httpclient, header);
    }
    
    public static String getByHttps(String url) {
        return getByHttps(url, new HashMap<String, String>());
    }
    
    public static String getByHttps(String url, Map<String, String> headers) {
        return getByHttps(url, null, headers);
    }
    
    public static String getByHttps(String url, String inputParam, Map<String, String> headers) {
        HttpClient httpclient = getInstance();
        return get(url, inputParam, httpclient, headers);
    }

    public static HttpResponse deleteByHttps(String url, Map<String, String> headers) {
        HttpClient httpclient = getInstance();
        return delete(url, httpclient, headers);
    }

    private static HttpResponse post(String url, HttpClient httpclient, Map<String, String> headers) {
        
    	HttpResponse response = null;
        
        try {
            httpclient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);// 连接超时
            httpclient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, SO_TIMEOUT); // 读取超时

            HttpPost httpPost = new HttpPost(url);
            for(String key : headers.keySet()) {
            	httpPost.addHeader(key, headers.get(key));
            }
            response = httpclient.execute(httpPost);
        } catch (Exception e) {
            LogUtils.exception(logger, e);
            throw new RuntimeException(MsgEnum.U_00002.getMessage());
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        
        return response;
    }

    private static String get(String url, String inputParam, HttpClient httpclient, Map<String, String> headers) {
        
        String response = null;
        
        try {
            httpclient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);// 连接超时
            httpclient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, SO_TIMEOUT); // 读取超时
            
            HttpGet httpGet;
            if(inputParam != null && inputParam.length() > 0) {
                httpGet = new HttpGet(url + "?" + inputParam);
            } else {
                httpGet = new HttpGet(url);
            }
            
            for(String key : headers.keySet()) {
                httpGet.addHeader(key, headers.get(key));
            }

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpclient.execute(httpGet, responseHandler);
        } catch (Exception e) {
            LogUtils.exception(logger, e);
            throw new RuntimeException(MsgEnum.U_00002.getMessage());
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        
        return response;
    }

    private static HttpResponse delete(String url, HttpClient httpclient, Map<String, String> headers) {
        
        HttpResponse response = null;
        
        try {
            httpclient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);// 连接超时
            httpclient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, SO_TIMEOUT); // 读取超时
            
            HttpDelete httpDelete = new HttpDelete(url);
            
            for(String key : headers.keySet()) {
                httpDelete.addHeader(key, headers.get(key));
            }

            response = httpclient.execute(httpDelete);
        } catch (Exception e) {
            LogUtils.exception(logger, e);
            throw new RuntimeException(MsgEnum.U_00002.getMessage());
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        
        return response;
    }
    public static void setTimeout(int timeout) {
        HttpClientUtils.CONNECTION_TIMEOUT = timeout;
    }

    private static HttpResponse put(String url, HttpClient httpclient, Map<String, String> headers, InputStream instream, Integer length) {
        HttpPut httpPut = new HttpPut(url);
        HttpResponse response = null;
        if(headers != null) {
            for(String key : headers.keySet()) {
                httpPut.addHeader(key, headers.get(key));
            }
        }
        
        InputStreamEntity ise = new InputStreamEntity(instream, length);
        httpPut.setEntity(ise);
        
        httpclient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);// 连接超时
        httpclient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, SO_TIMEOUT); // 读取超时
        
        try {
            response = httpclient.execute(httpPut);
        } catch (Exception e) {
            LogUtils.exception(logger, e);
            throw new RuntimeException(MsgEnum.U_00002.getMessage());
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

        return response;
    }

    private static HttpResponse head(String url, HttpClient httpclient, Map<String, String> headers) {
        HttpHead httpHead = new HttpHead(url);
        HttpResponse response = null;
        if(headers != null) {
            for(String key : headers.keySet()) {
            	httpHead.addHeader(key, headers.get(key));
            }
        }
        
        httpclient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);// 连接超时
        httpclient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, SO_TIMEOUT); // 读取超时
        
        try {
            response = httpclient.execute(httpHead);
        } catch (Exception e) {
            LogUtils.exception(logger, e);
            throw new RuntimeException(MsgEnum.U_00002.getMessage());
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

        return response;
    }
}
