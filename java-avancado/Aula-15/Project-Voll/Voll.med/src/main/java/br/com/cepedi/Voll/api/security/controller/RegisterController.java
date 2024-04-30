package br.com.cepedi.Voll.api.security.controller;


import br.com.cepedi.Voll.api.security.model.entitys.User;
import br.com.cepedi.Voll.api.security.model.records.details.DadosTokenJWT;
import br.com.cepedi.Voll.api.security.model.records.details.DataDetailsRegisterUser;
import br.com.cepedi.Voll.api.security.model.records.input.DataAuth;
import br.com.cepedi.Voll.api.security.model.records.input.DataRegisterUser;
import br.com.cepedi.Voll.api.security.service.AuthService;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private AuthService authService;




    @PostMapping
    public DataDetailsRegisterUser registerUser(@RequestBody @Valid DataRegisterUser data) {

        DataDetailsRegisterUser dataDetailsRegisterUser = authService.register(data);
        return dataDetailsRegisterUser;
    }

}
