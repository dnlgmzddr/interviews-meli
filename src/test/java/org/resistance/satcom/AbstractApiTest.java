package org.resistance.satcom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SpringTestConfig.class, JacksonAutoConfiguration.class})
public abstract class AbstractApiTest {

	@Autowired
	protected MockMvc mvc;

	@MockBean
	private RestTemplate http;

	@Autowired
	private ObjectMapper jsonMapper;

	@SneakyThrows
	protected String jsonify(Object value) throws JsonProcessingException {
		return jsonMapper.writeValueAsString(value);
	}

}
