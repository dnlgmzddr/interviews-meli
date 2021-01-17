package org.resistance.satcom.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MessageServiceTest {

    @Test
    public void getMessage(){
        var message = new MessageService().getMessages(
                List.of("este", "", "", "mensaje", ""),
                List.of("", "es", "", "", "secreto"),
                List.of("este", "", "un", "", "")
        );
        Assertions.assertTrue(message.isPresent());
        Assertions.assertEquals(message.get(), "este es un mensaje secreto");
    }
}
