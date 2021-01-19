package org.resistance.satcom.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MessageService {

    @SafeVarargs
    public final Optional<String> getMessages(List<String>... messages) {
        var aa = Arrays.asList(messages);
        return this.getMessages(aa);
    }

    public final Optional<String> getMessages(List<List<String>> messages) {
        if (messages.stream().map(List::size).distinct().count() != 1) {
            return Optional.empty();
        }
        var result = new String[(messages.get(0).size())];
        Arrays.fill(result, "");
        for (List<String> message : messages) {
            for (int i = 0; i < message.size(); i++) {
                result[i] = message.get(i).equals("") ? result[i] : message.get(i);
            }
        }
        return Optional.of(String.join(" ", result));
    }
}
