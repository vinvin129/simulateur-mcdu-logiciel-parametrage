package fr.ptut2022.simulateur_mcdu.handlers;

import fr.ptut2022.simulateur_mcdu.ControlClickResult;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;

public class ControlClickHandler implements StompFrameHandler {
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return ControlClickResult.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        ControlClickResult clickResult = (ControlClickResult) payload;
        System.out.println(clickResult.getKey() + " : " + clickResult.getInput());
    }
}
