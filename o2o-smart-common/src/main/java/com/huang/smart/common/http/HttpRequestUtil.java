//package com.huang.smart.common;
//
//import java.io.IOException;
//import java.net.URI;
//
//import org.apache.http.Consts;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import com.alibaba.fastjson.JSON;
//import com.toucha.core.httpClient.HttpClientUtils;
//import com.toucha.core.logging.enums.GeneralAppEvent;
//import com.toucha.core.logging.util.AppEvents;
//
///**
//* http 请求类
//*/
//public class HttpRequestUtil {
//
//    private static final int CONNECTION_TIMEOUT = 10 * 1000;
//
//    private static final int SOCKET_TIMEOUT = 20 * 1000;
//
//    private static HttpClient createHttpClient() {
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT)
//                .setConnectionRequestTimeout(CONNECTION_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
//        return HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
//    }
//
//    /**
//     * get请求
//     * */
//    public static <T> T sendHttpRequest(String url, Class<T> responseType) {
//        return HttpClientUtils.get(url, createHttpClient(), new JDHomeResponseHandler<>(responseType));
//    }
//
//    /**
//     * post请求
//     * */
//    public static <T> T sendHttpRequest(String url, Object body, Class<T> responseType) {
//        String bodyString = body == null ? "" : JSON.toJSONString(body);
//        HttpEntity requestEntity = new StringEntity(bodyString, Consts.UTF_8);
//        return HttpClientUtils.post(url, createHttpClient(), requestEntity, new JDHomeResponseHandler<>(responseType));
//    }
//
//    private static class JDHomeResponseHandler<T> implements ResponseHandler<T> {
//
//        private Class<T> responseType;
//
//        public JDHomeResponseHandler(Class<T> responseType) {
//            this.responseType = responseType;
//        }
//
//        @Override
//        public T handleResponse(HttpResponse response) throws IOException {
//            HttpEntity repsonseEntity = response.getEntity();
//            String jsonObj = EntityUtils.toString(repsonseEntity, Consts.UTF_8);
//            int result = response.getStatusLine().getStatusCode();
//            if (result == HttpStatus.SC_OK) {
//                return JSON.parseObject(jsonObj, responseType);
//            } else {
//                AppEvents.LogError(GeneralAppEvent.FATAL_REQUEST_ERROR, jsonObj);
//                throw new RuntimeException("请求失败！");
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        String uri = "https://testpdjm.jd.com/client?functionId=share/bindByPhone&body={\"token\":\"eZHu6Zu3Zj+Bowjd4O+I8ZMXRJvGNF4GZAA/AC4kwt96Ow7VgBgJOMqMFwcDSoq5bxMyCScMFsxN1Cjn5h9boAvlJAcXULykP375XgiMNvA=\"}";
//        URI.create(uri);
//        //        HttpGet httpGet = new HttpGet();
//    }
//}
