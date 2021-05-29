package rs.ac.uns.ftn.devops.tim5.agentorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgentOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentOrderApplication.class, args);
    }

}
