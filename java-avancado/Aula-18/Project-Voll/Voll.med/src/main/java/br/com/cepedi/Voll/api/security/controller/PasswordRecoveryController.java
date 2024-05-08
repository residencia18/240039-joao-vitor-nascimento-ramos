package br.com.cepedi.Voll.api.security.controller;

import br.com.cepedi.Voll.api.security.model.entitys.User;
import br.com.cepedi.Voll.api.security.model.records.input.DataRequestResetPassword;
import br.com.cepedi.Voll.api.security.model.records.input.DataResetPassword;
import br.com.cepedi.Voll.api.security.service.TokenService;
import br.com.cepedi.Voll.api.security.service.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.StringWriter;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/reset-password/")
public class PasswordRecoveryController {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private Configuration freemarkerConfig;

    private void sendResetPasswordEmail(String email, String token) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("Password Reset");

            // Processando o modelo HTML
            Map<String, Object> model = new HashMap<>();
            model.put("token", token);
            String htmlBody = processHtmlTemplate("reset_password_email_template.html", model);

            helper.setText(htmlBody, true);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Lidar com a exceção
        }
    }

    private String processHtmlTemplate(String templateName, Map<String, Object> model) {
        try {
            Template template = freemarkerConfig.getTemplate(templateName);
            StringWriter stringWriter = new StringWriter();
            template.process(model, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception
            return "";
        }
    }

    @PostMapping("/request")
    public ResponseEntity<String> resetPasswordRequest(@RequestBody DataRequestResetPassword dataResetPassword) {
        User user = userService.getUserActivatedByEmail(dataResetPassword.email());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("E-mail not found");
        }

        // Generate a token for the user
        String token = tokenService.generateTokenRecoverPassword(user);

        // Send an email with the recovery link containing the token
        sendResetPasswordEmail(dataResetPassword.email(), token);

        String responseMessage = "A password reset email has been sent to " + dataResetPassword.email();
        return ResponseEntity.ok(responseMessage);
    }


    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@Valid DataResetPassword dataResetPassword) {
        // Validate the token
        if (tokenService.isValidToken(dataResetPassword.token())) {
            // Get the email associated with the token
            String email = tokenService.getEmailByToken(dataResetPassword.token());
            // Update user's password
            userService.updatePassword(email, dataResetPassword.password());
            tokenService.revokeToken(dataResetPassword.token());
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token");
        }
    }


}
