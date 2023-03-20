package fr.ptut2022.simulateur_mcdu;

import fr.ptut2022.simulateur_mcdu.handlers.ControlClickHandler;
import fr.ptut2022.simulateur_mcdu.handlers.LskClickHandler;
import org.springframework.messaging.simp.stomp.*;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/setting/receive/click/lsk", new LskClickHandler());
        session.subscribe("/setting/receive/click/control", new ControlClickHandler());
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
}
