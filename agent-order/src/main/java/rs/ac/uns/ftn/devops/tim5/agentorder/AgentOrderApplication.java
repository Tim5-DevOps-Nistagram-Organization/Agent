package rs.ac.uns.ftn.devops.tim5.agentorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@RestController
public class AgentOrderApplication {

    @RequestMapping("/health")
    public String home() {
        return "Hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(AgentOrderApplication.class, args);
    }

}
