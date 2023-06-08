package org.simulation;

import org.http_playground.HttpClient;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        HttpClient client = new HttpClient("api.deltacrypt.net", 5000, "user/from-id");
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("user_id", "3");
        client.sendGetRequest(queryParameters);
    }

}