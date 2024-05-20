package ro.sorin.pynt;

import org.citrusframework.container.AfterSuite;
import org.citrusframework.container.SequenceAfterSuite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.citrusframework.container.BeforeSuite;
import org.citrusframework.container.SequenceBeforeSuite;

@Configuration
public class TodoAppAutoConfiguration {
    @Bean
    @ConditionalOnProperty(name = "system.under.test.mode", havingValue = "embedded")
    public BeforeSuite embeddedTodoApp() {
        return new SequenceBeforeSuite.Builder()
                .actions(context -> SpringApplication.run(PyntApplication.class))
                .build();
    }




}
