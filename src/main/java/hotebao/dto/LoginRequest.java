package hotebao.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Email deve ter um formato válido")
    private String email;

    @NotBlank(message = "Senha deve ter formato válido")
    private String senha;
}
