package org.resistance.satcom.services;

import java.util.*;
import java.util.stream.Collectors;

public class MessageService {

    @SafeVarargs
    public final Optional<String> getMessages(List<String>... messages) {
        if (Arrays.stream(messages).map(List::size).distinct().count() != 1) {
            return Optional.empty();
        }
        var result = new String[(messages[0].size())];
        Arrays.fill(result, "");
        for (List<String> message : messages) {
            for (int i = 0; i < message.size(); i++) {
                result [i] = message.get(i).equals("") ? result[i] : message.get(i);
            }
        }
        return Optional.of(String.join(" ", result));
    }
}
