package statkevich.scooters.controller.appConfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "statkevich.scooters.controller")
public class ControllerConfig {

    public ControllerConfig() {
    }
}
