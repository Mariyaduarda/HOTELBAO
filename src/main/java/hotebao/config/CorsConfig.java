package hotebao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 permite que seu backend seja acessado por qualquer frontend

libera requisições de qualquer domínio ("*")

autoriza métodos HTTP: GET, POST, PUT, DELETE, OPTIONS

necessário quando frontend e backend estão em domínios/portas diferentes

 cross origin resource sharing - compartilhamento de recursos entre origens */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "DELETE", "OPTIONS", "PUT")
                        .allowedOrigins("*");

            }
        };
    }
}
