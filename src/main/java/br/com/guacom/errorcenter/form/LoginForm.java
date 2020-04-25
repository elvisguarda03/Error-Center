package br.com.guacom.errorcenter.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    @NotBlank(message = "E-mail must not be null")
    private String email;

    @NotBlank(message = "Password must not be null")
    private String password;

    public UsernamePasswordAuthenticationToken toUser() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
