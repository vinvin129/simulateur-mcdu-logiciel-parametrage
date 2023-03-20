package fr.ptut2022.simulateur_mcdu.handlers;

import fr.ptut2022.simulateur_mcdu.Donnee;
import fr.ptut2022.simulateur_mcdu.LSKClickResult;
import fr.ptut2022.simulateur_mcdu.Main;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;

public class LskClickHandler implements StompFrameHandler {
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return LSKClickResult.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        LSKClickResult clickResult = (LSKClickResult) payload;
        System.out.println(clickResult.getKey() + " : " + clickResult.getInput());
        try {
            Main.updateView(clickResult.getKey(), new Donnee("toto", clickResult.getInput(), "red"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
