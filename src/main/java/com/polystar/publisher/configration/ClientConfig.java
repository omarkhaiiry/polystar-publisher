package com.polystar.publisher.configration;


import com.polystar.publisher.props.Props;
import com.polystar.publisher.template.ClientTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableConfigurationProperties(Props.class)
public class ClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public ClientTemplate clientTemplate(Props props, RestTemplate restTemplate) {
        return new ClientTemplate(props, restTemplate);
    }
}
