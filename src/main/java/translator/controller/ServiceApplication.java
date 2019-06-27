package translator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({"translator.controller"})
public class ServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(ServiceApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }


}

