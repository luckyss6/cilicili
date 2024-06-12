package wilson.cilicili.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Test
    void videoTest(@Autowired WebTestClient webTestClient) {
        webTestClient.get().uri("/user/video").exchange().expectStatus().isOk();
    }
}