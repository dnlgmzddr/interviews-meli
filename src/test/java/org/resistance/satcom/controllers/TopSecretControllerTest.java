package org.resistance.satcom.controllers;

import org.junit.jupiter.api.Test;
import org.resistance.satcom.AbstractApiTest;
import org.resistance.satcom.models.Satellite;
import org.resistance.satcom.models.TopsecretRequest;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TopSecretControllerTest extends AbstractApiTest {

    @Test
    public void shouldFailed() throws Exception {
        var payload = new  TopsecretRequest(List.of(
                        new Satellite("kenobi", 100d, List.of("este", "", "", "mensaje", "")),
                        new Satellite("skywalker", 115.5, List.of("", "es", "", "", "secreto")),
                        new Satellite("sato", 142.7, List.of("este", "", "un", "", ""))
                ));

        var request = post("/topsecret")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonify(payload));

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

}
