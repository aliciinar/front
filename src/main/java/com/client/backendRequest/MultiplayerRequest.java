package com.client.backendRequest;


import com.client.ClientApplication;
import com.client.dto.*;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLOutput;

/**
 * Html request are contained in this class during the multiplayer game
 */

public class MultiplayerRequest {

    /**
     * Response getter for Http post request
     * @param post httppost type
     * @param params
     * @return response of the data base
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

    /**
     * get response from database while turn of the game in other player
     * @param get request type
     * @return
     * @throws IOException
     */
    public ResponseEntity<String> getResponse(HttpGet get) throws IOException {

        HttpResponse response = ClientApplication.httpClient.execute(get);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try (InputStream inputStream = entity.getContent()) {
                String content = EntityUtils.toString(entity);
                if(content.length() != 0){
                    return  new ResponseEntity<>(content,HttpStatus.resolve(response.getStatusLine().getStatusCode()));
                }
                else {
                    return new ResponseEntity<>(content,HttpStatus.resolve(response.getStatusLine().getStatusCode()));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return new ResponseEntity<>("Fail",HttpStatus.resolve(response.getStatusLine().getStatusCode()));
    }

    /**
     *  try to find a game in order to play a game
     * @param userName username of the player
     * @param token token of the player
     * @return matching game
     */
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

    /**
     * get actions when turn of the game in other player
     * @param userName username of the user
     * @param token token of the user
     * @return action  of the other player
     */
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

    /**
     * delete last action when take an action from other player in order do not create a misunderstanding
     * @param userName username of the user
     * @param token   token of the uesr
     * @return http response
     */
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

    /**
     * add actions to the database when the game turn is on the player
     * @param userName  username of the player
     * @param type type of the action
     * @param dice1 dice value
     * @param dice2 dice value
     * @param token token of the user
     * @return http status
     */
    public ResponseEntity<String> addAction(String userName , String type , int dice1 , int dice2 ,String token) {
        HttpPost post = new HttpPost(ClientApplication.backend + "addAction");
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

    /**
     * delete the game information from the table when game finish
     * @param token token of the use r
     * @return http status
     */
    public ResponseEntity<String> deleteAllGames(String token) {
        HttpGet post = new HttpGet(ClientApplication.backend + "deleteGames");
        try {
            post.setHeader("Authorization", "Bearer " + token);
            return getResponse(post);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong" , HttpStatus.BAD_GATEWAY);

    }


}
