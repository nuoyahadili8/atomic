package com.github.al.authority.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/12 18:20
 * @Modified By:
 */
public class AuthorityMain {

    public static void main(String[] args) throws IOException {
        //String url = "http://localhost:5005/oauth/token?username=admin&password=password&grant_type=password&scope=webapp&client_id=atomic&client_secret=atomic";
        String url="http://localhost:8080/auth/oauth/token?username=admin&password=password&grant_type=password&scope=webapp&client_id=atomic&client_secret=atomic";
        HttpPost post=new HttpPost(url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = httpclient.execute(post);
//        System.out.println(">>>>>>>>"+response.getStatusLine().getStatusCode());
        System.out.println(EntityUtils.toString(response.getEntity()));

    }
}
