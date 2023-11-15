package be.ww.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
    @GetMapping("ping")
    public String ping() {
        return "pong";
    }
}
