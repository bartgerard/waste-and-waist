package be.ww.user;

import be.ww.user.config.UserConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        UserConfiguration.class
})
@ComponentScan({
        "be.ww.user.web"
})
public class UserModule {
}
