package com.client;


import com.client.dto.LoginDto;
import com.client.dto.RegisterDto;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Request {


    public String  getResponse(HttpPost post , StringEntity params) throws IOException {
        post.setEntity(params);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = ClientApplication.httpClient.execute(post);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try (InputStream inputStream = entity.getContent()) {
                String content = EntityUtils.toString(entity);
                if(content.length() != 0){
                    return content;
                }
                else {
                    return "";
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }

    public String loginRequest(String userName , String password){
        HttpPost post = new HttpPost(ClientApplication.backend + "login");
        try {
            StringEntity params = new StringEntity(ClientApplication.gson.toJson(new LoginDto(userName,password)));
            return getResponse(post , params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String addWeekRequest(int score , String userName , String password){
        HttpPost post = new HttpPost(ClientApplication.backend + "login");
        try {
            StringEntity params = new StringEntity(ClientApplication.gson.toJson(new LoginDto(userName,password)));
            return getResponse(post , params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String RegisterRequest(String userName , String password , String email) {
        HttpPost post = new HttpPost(ClientApplication.backend + "register");
        try {
            StringEntity params = new StringEntity(ClientApplication.gson.toJson(new RegisterDto(userName, password, email)));
            return getResponse(post, params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
