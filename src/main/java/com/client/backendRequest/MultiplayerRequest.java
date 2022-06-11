package com.client.backendRequest;


import com.client.ClientApplication;
import com.client.dto.*;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Html request are contained in this class
 */

public class MultiplayerRequest {

    /**
     * Response getter for Http post request
     * @param post
     * @param params
     * @return
     * @throws IOException
     */
    public ResponseEntity<String> getResponse (HttpPost post , StringEntity params) throws IOException {
        post.setEntity(params);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = ClientApplication.httpClient.execute(post);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try (InputStream inputStream = entity.getContent()) {
                String content = EntityUtils.toString(entity);
                if(content.length() != 0){
                    return new ResponseEntity<>(content,HttpStatus.resolve(response.getStatusLine().getStatusCode()));
                }
                else {
                    return new ResponseEntity<>("",HttpStatus.resolve(response.getStatusLine().getStatusCode()));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return new ResponseEntity<>("",HttpStatus.resolve(response.getStatusLine().getStatusCode()));
    }


    public ResponseEntity<String> playMultiplayer(String userName , String token) {
        HttpPost post = new HttpPost(ClientApplication.backend + "findGame");
        try {
            StringEntity params = new StringEntity(ClientApplication.gson.toJson(new GameDto(userName)));
            post.setHeader("Authorization", "Bearer " + token);
            return getResponse(post, params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong" , HttpStatus.BAD_GATEWAY);

    }

    public ResponseEntity<String> getAction(String userName , String token) {
        HttpPost post = new HttpPost(ClientApplication.backend + "getAction");
        try {
            StringEntity params = new StringEntity(ClientApplication.gson.toJson(new GameDto(userName)));
            post.setHeader("Authorization", "Bearer " + token);
            return getResponse(post, params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong" , HttpStatus.BAD_GATEWAY);

    }

    public ResponseEntity<String> deleteAction(String userName , String token) {
        HttpPost post = new HttpPost(ClientApplication.backend + "deleteAction");
        try {
            StringEntity params = new StringEntity(ClientApplication.gson.toJson(new GameDto(userName)));
            post.setHeader("Authorization", "Bearer " + token);
            return getResponse(post, params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong" , HttpStatus.BAD_GATEWAY);

    }
    public ResponseEntity<String> addAction(String userName , String type , int dice1 , int dice2 ,String token) {
        HttpPost post = new HttpPost(ClientApplication.backend + "deleteAction");
        try {
            StringEntity params = new StringEntity(ClientApplication.gson.toJson(new ActionDto(userName , type , dice1 , dice2)));
            post.setHeader("Authorization", "Bearer " + token);
            return getResponse(post, params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong" , HttpStatus.BAD_GATEWAY);

    }



}
