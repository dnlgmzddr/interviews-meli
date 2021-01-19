package org.resistance.satcom.controllers;

import org.junit.jupiter.api.Test;
import org.resistance.satcom.AbstractApiTest;
import org.resistance.satcom.models.Satellite;
import org.resistance.satcom.models.TopsecretRequest;
import org.springframework.http.MediaType;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class TopSecretControllerTest extends AbstractApiTest {

    @Test
    public void shouldFailed() throws Exception {
        var payload = new TopsecretRequest(List.of(
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

    @Test
    public void shouldPass() throws Exception {
        var payload = new TopsecretRequest(List.of(
                new Satellite("kenobi", 538.516, List.of("este", "", "", "mensaje", "")),
                new Satellite("skywalker", 141.421, List.of("", "es", "", "", "secreto")),
                new Satellite("sato", 509.901, List.of("este", "", "un", "", ""))
        ));

        var request = post("/topsecret")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonify(payload));

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.position.x", is(0)))
                .andExpect(jsonPath("$.position.y", is(0)))
        .andExpect(jsonPath("$.message", is("este es un mensaje secreto")));
    }

}
