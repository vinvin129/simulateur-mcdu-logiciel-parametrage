package fr.ptut2022.simulateur_mcdu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static String URL = "ws://localhost:8080/gs-guide-websocket";
    private static final Map<LSKKey, Donnee> mapping = new HashMap<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");

        System.out.println("Connecting to websocket server...");
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        stompClient.connect(URL, sessionHandler);

        System.out.println("Setting MCDU...");
        updateView(LSKKey.LSK1L, new Donnee("Test", "Test", "white"));
        updateView(LSKKey.LSK1R, new Donnee("Test", "Test", "white"));
        updateView(LSKKey.LSK2L, new Donnee("Test", "Test", "white"));
        updateView(LSKKey.LSK2R, new Donnee("Test", "Test", "white"));
        updateView(LSKKey.LSK3L, new Donnee("Test", "Test", "white"));
        updateView(LSKKey.LSK3R, new Donnee("Test", "Test", "white"));
        updateView(LSKKey.LSK4L, new Donnee("Test", "Test", "white"));
        updateView(LSKKey.LSK4R, new Donnee("Test", "Test", "white"));
        updateView(LSKKey.LSK5L, new Donnee("Test", "Test", "white"));
        updateView(LSKKey.LSK5R, new Donnee("Test", "Test", "white"));
        updateView(LSKKey.LSK6L, new Donnee("Test", "Test", "white"));
        updateView(LSKKey.LSK6R, new Donnee("Test", "Test", "white"));

        System.out.println("Press 'Enter' to close the program.");



        new Scanner(System.in).nextLine();
    }

    public static void updateView(LSKKey key, Donnee donnee) throws Exception {
        mapping.put(key, donnee);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpUriRequest post = new HttpPost("http://localhost:8080/setting/emit/mapping/" + key.toString());

            ObjectMapper objectMapper = new ObjectMapper();

            StringEntity input = new StringEntity(objectMapper.writeValueAsString(donnee));
            post.setEntity(input);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");

            try (CloseableHttpResponse response = httpclient.execute(post)) {
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        }
    }
}