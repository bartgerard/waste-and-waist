package be.ww.app;

import be.ww.configuration.AxonConfiguration;
import be.ww.configuration.ElasticSearchConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        AxonConfiguration.class,
        ElasticSearchConfiguration.class,
        TestRestController.class
})
public class WasteAndWaistApplication {
    public static void main(String[] args) {
        SpringApplication.run(WasteAndWaistApplication.class, args);
    }
}
