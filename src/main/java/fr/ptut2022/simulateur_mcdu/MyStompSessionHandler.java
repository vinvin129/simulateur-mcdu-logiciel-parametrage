package fr.ptut2022.simulateur_mcdu;

import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;
import java.util.Arrays;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/setting/receive/click", this);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        System.err.println("handleException : ");
        exception.printStackTrace();
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        System.err.println("handleTransportError : ");
        exception.printStackTrace();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return ClickResult.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        ClickResult clickResult = (ClickResult) payload;
        System.out.println(clickResult.getLskKey() + " : " + clickResult.getInput());
    }
}
