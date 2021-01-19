package org.resistance.satcom.controllers;

import org.junit.jupiter.api.Test;
import org.resistance.satcom.AbstractApiTest;
import org.resistance.satcom.models.Satellite;
import org.resistance.satcom.models.TopsecretRequest;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class TopSecretControllerTest extends AbstractApiTest {

    @Test
    public void shouldFailed() throws Exception {
        var payload = Map.of("satellites", List.of(
                Map.of("name", "kenobi", "distance", 100d, "message", List.of("este", "", "", "mensaje", "")),
                Map.of("name", "skywalker", "distance", 115.5, "message", List.of("", "es", "", "", "secreto")),
                Map.of("name", "sato", "distance", 142.7, "message", List.of("este", "", "un", "", ""))
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


        var payload = Map.of("satellites", List.of(
                Map.of("name", "kenobi", "distance", 538.516, "message", List.of("este", "", "", "mensaje", "")),
                Map.of("name", "skywalker", "distance", 141.421, "message", List.of("", "es", "", "", "secreto")),
                Map.of("name", "sato", "distance", 509.901, "message", List.of("este", "", "un", "", ""))
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
