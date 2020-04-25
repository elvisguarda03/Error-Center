package br.com.guacom.errorcenter.controller;

import br.com.guacom.errorcenter.dto.TokenDTO;
import br.com.guacom.errorcenter.form.LoginForm;
import br.com.guacom.errorcenter.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginForm form) {
        try {
            Authentication authenticate = authManager.authenticate(form.toUser());
            String token = tokenService.generateToken(authenticate);

            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException ae) {
            return ResponseEntity.badRequest().build();
        }
    }
}
