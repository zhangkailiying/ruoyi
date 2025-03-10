package com.ruoyi.project.system.domain.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SportteryCrawler {

    private static final String BASE_URL = "https://webapi.sporttery.cn";
    private static final String[] USER_AGENTS = {
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3",
            "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; AS; rv:11.0) like Gecko",
            "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0",
            // Add more User-Agent strings if needed
    };

    private static final Random RANDOM = new Random();

    private static String sendGetRequest(String endpoint) throws Exception {
        URL url = new URL(BASE_URL + endpoint);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("47.120.0.231", 8081)); // Replace with your proxy host and port if needed
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENTS[RANDOM.nextInt(USER_AGENTS.length)]);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        // Add a random delay between requests to prevent IP blocking
        TimeUnit.SECONDS.sleep(RANDOM.nextInt(5) + 1);

        return content.toString();
    }

    public static void main(String[] args) {
        try {
            String response = sendGetRequest("/jc/zqsgkj"); // Replace with actual endpoint
            Gson gson = new Gson();
            Map<String, Object> result = gson.fromJson(response, new TypeToken<HashMap<String, Object>>(){}.getType());

            System.out.println("Response: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}