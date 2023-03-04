package fr.ptut2022.simulateur_mcdu;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static String URL = "ws://localhost:8080/gs-guide-websocket";

    public static void main(String[] args) {
        System.out.println("Hello world!");

        System.out.println("Connecting to websocket server...");
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        stompClient.connect(URL, sessionHandler);

        System.out.println("Press 'Enter' to close the program.");



        new Scanner(System.in).nextLine();
    }
}