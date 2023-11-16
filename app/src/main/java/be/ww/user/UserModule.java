package be.ww.user;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "be.ww.user.web"
})
public class UserModule {
}
