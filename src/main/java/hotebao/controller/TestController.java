package hotebao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        return "🚀 Aplicação funcionando! H2 Console disponível em: http://localhost:8080/h2-console";
    }

    @GetMapping("/health")
    public String health() {
        return "✅ OK - Aplicação rodando na porta 8080";
    }
}