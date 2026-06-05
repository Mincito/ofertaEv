package cl.duoc.ofertaEv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient subastaWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8087/api/v1/subastas")
                .build();
    }
}