package rs.ac.uns.ftn.devops.tim5.agentproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgentProductApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AgentProductApplication.class, args);
    }

}
