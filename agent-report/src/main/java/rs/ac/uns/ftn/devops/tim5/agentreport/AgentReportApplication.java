package rs.ac.uns.ftn.devops.tim5.agentreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgentReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentReportApplication.class, args);
    }

}
