package cl.duoc.ofertaEv.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient subastaWebClient(
            @Value("${subasta.service.url}") String subastaServiceUrl) {

        return WebClient.builder()
                .baseUrl(subastaServiceUrl)
                .build();
    }
}