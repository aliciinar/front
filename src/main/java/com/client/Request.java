package com.client;


import com.client.dto.ForgetPasswordDto;
import com.client.dto.LoginDto;
import com.client.dto.RegisterDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

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

    public String getResponse(HttpGet get) throws IOException {

        HttpResponse response = ClientApplication.httpClient.execute(get);
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
        return "Error";
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

    public String forgotPasswordRequest(String email) {
        HttpPost post = new HttpPost(ClientApplication.backend + "forgot_password");
        try {
            StringEntity params = new StringEntity(ClientApplication.gson.toJson(new ForgetPasswordDto(email)));
            return getResponse(post, params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public JSONArray GetWeekTableRequest(String token) {
        HttpGet get = new HttpGet(ClientApplication.backend + "ScoresWeek");
        get.setHeader("Authorization", "Bearer " + token);
        try {
            String ret = getResponse(get);
            JSONArray temp1 = new JSONArray(ret) ;
            return temp1;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray GetMonthTableRequest(String token) {
        HttpGet get = new HttpGet(ClientApplication.backend + "ScoresMonth");
        get.setHeader("Authorization", "Bearer " + token);
        try {
            String ret = getResponse(get );
            JSONArray temp1 = new JSONArray(ret) ;
            return temp1;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }




}
