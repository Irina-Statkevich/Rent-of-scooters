package statkevich.scooters.dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan
@ComponentScan(basePackages = "statkevich.scooters.dao")
public class DAOConfig {

    public DAOConfig() {
    }

}
