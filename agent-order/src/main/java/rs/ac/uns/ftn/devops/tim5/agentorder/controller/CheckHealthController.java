package rs.ac.uns.ftn.devops.tim5.agentorder.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("dev")
public class CheckHealthController {

    @GetMapping("/")
    public String check() {
        return "Order service!";
    }
}
