package hotebao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "hotebao.repository")
@EntityScan(basePackages = "hotebao.entity") // or wherever your entities are
public class HotelbaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelbaoApplication.class, args);
    }
}