package ro.sorin.pynt;

import org.citrusframework.dsl.endpoint.CitrusEndpoints;
import org.citrusframework.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Import(TodoAppAutoConfiguration.class)
@Configuration
public class EndpointConfig {

    @Bean
    public HttpClient httpClient() {
        return CitrusEndpoints
                .http()
                .client()
                .requestUrl("http://192.168.1.146:8080")
                //.requestUrl("http://localhost:6666")
                .build();
    }

}
