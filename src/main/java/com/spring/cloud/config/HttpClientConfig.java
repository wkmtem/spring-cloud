package com.spring.cloud.config;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * Class Name: HttpClientConfig
 * Package: com.spring.cloud.config
 * Description:
 * @author wkm
 * Create DateTime: 2017/12/14 上午4:20
 * Version: 1.0
 */
@SpringBootConfiguration
public class HttpClientConfig {

    @Autowired
    private RestTemplateBuilder builder;

    /**
     * Method Name: restTemplate
     * Description: RestTemplate底层默认的JDK实现
     * Create DateTime: 2017/12/14 上午4:25
     * @return
     */
    @Bean
    /** 开启负载均衡 */
    @LoadBalanced
    public RestTemplate restTemplate() {
        /** 方式一 */
        //return new RestTemplate();
        /** 方式二 */
        return builder.build();
    }

    /**
     * Method Name: okHttp3Client
     * Description: RestTemplate底层支持的okHttp3实现
     * Create DateTime: 2017/12/14 上午5:08
     * @return
     */
    @Bean
    /** 开启负载均衡 */
    @LoadBalanced
    public RestTemplate okHttp3Client() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }


    /**********************************  配置超时时间  **********************************/
    /**
     * Method Name: okHttpClient
     * Description: OkHttp3独立的实现的Client
     * Create DateTime: 2017/12/14 上午5:28
     * @return
     */
    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return builder.build();
    }
    /**********************************  配置超时时间  **********************************/


    /***********************************  配置https  ***********************************/
    /*@Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .sslSocketFactory(getTrustedSSLSocketFactory())
                .hostnameVerifier(DO_NOT_VERIFY);
        return builder.build();
    }

    TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    X509Certificate[] x509Certificates = new X509Certificate[0];
                    return x509Certificates;
                }
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
    };

    HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    private SSLSocketFactory getTrustedSSLSocketFactory() {
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            return sc.getSocketFactory();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }*/
    /***********************************  配置https  ***********************************/


    /********************************  配置interceptor  ********************************/
    /*@Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request requestWithUserAgent = originalRequest.newBuilder()
                                .header(USER_AGENT_HEADER_NAME, CUSTOM_USER_AGENT)
                                .build();
                        return chain.proceed(requestWithUserAgent);
                    }
                });
        return builder.build();
    }*/
    /********************************  配置interceptor  ********************************/
}
