package hotebao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

// Temporariamente desabilitar segurança para testar JPA
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "hotebao.repository")
@EntityScan(basePackages = "hotebao.entity")
public class HotelbaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelbaoApplication.class, args);
    }
}