package br.com.cepedi.Voll.api.security.controller;

import br.com.cepedi.Voll.api.security.model.entitys.User;
import br.com.cepedi.Voll.api.security.model.records.input.DataResetPassword;
import br.com.cepedi.Voll.api.security.service.TokenService;
import br.com.cepedi.Voll.api.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/reset-password")
public class PasswordRecoveryController {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/request")
    public String resetPasswordRequest(@RequestBody DataResetPassword dataResetPassword) {
        User user = userService.getUserActivatedByEmail(dataResetPassword.email());
        if (user == null) {
            return "E-mail not found";
        }

        // Gera um token para o usuário
        String token = tokenService.generateToken(user);

        // Envia um e-mail com o link de recuperação contendo o token
        sendResetPasswordEmail(dataResetPassword.email(), token);


        return "Um e-mail de redefinição de senha foi enviado para " + dataResetPassword.email();
    }

    @GetMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestParam(name = "token", required = false) String token) {

        String redirectUrl = "/reset-password/reset";

        System.out.println("token para validate " + token);

        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body("Token não fornecido");
        }
        if (tokenService.isValidToken(token)) {
            // Se o token for válido, redirecione o usuário para o URL fornecido
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUrl)).build();
        } else {
            return ResponseEntity.badRequest().body("Token inválido ou expirado");
        }
    }

    @GetMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam(name = "token", required = false) String token, @RequestParam(name = "password", required = false) String password) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body("Token não fornecido");
        }
        if (password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Senha não fornecida");
        }

        System.out.println(token);
        System.out.println(password);

        // Valida o token
        if (tokenService.isValidToken(token)) {
            // Obtém o e-mail associado ao token
            String email = tokenService.getEmailByToken(token);
            // Atualiza a senha do usuário
            userService.updatePassword(email, password);
            return ResponseEntity.ok("Senha atualizada com sucesso");
        } else {
            return ResponseEntity.badRequest().body("Token inválido ou expirado");
        }
    }

    private void sendResetPasswordEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Redefinição de senha");
        message.setText("Olá,\n\nVocê solicitou a redefinição de senha. Clique no link a seguir para redefinir sua senha:\n\nhttp://localhost:8080/reset-password/validate-token?token=" + token + "\n\nSe você não solicitou a redefinição de senha, ignore este e-mail.\n\nAtenciosamente,\nEquipe de suporte");
        System.out.println(message);
        emailSender.send(message);
    }
}
