package br.com.cepedi.Library.api.security.controller;


import br.com.cepedi.Library.api.security.model.entitys.User;
import br.com.cepedi.Library.api.security.model.records.input.DataAuth;
import br.com.cepedi.Library.api.security.model.records.output.DadosTokenJWT;
import br.com.cepedi.Library.api.security.service.TokenService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    public static final Logger log = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> loginAttempt(@RequestBody @Valid DataAuth data) {
        log.info("Login attempt with user: {}", data.login());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication authentication = manager.authenticate(authenticationToken);

        String tokenJWT = tokenService.generationToken((User) authentication.getPrincipal());
        log.info("Successful login for user: {}", data.login());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
