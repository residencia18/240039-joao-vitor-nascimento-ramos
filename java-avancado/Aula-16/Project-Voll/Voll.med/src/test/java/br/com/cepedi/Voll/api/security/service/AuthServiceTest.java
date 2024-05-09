package br.com.cepedi.Voll.api.security.service;


import br.com.cepedi.Voll.api.security.model.entitys.User;
import br.com.cepedi.Voll.api.security.model.records.details.DataDetailsRegisterUser;
import br.com.cepedi.Voll.api.security.model.records.input.DataRegisterUser;
import br.com.cepedi.Voll.api.security.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("Test loadUserByUsername when user exists")
    public void testLoadUserByUsernameWhenUserExists() {
        // Dado
        String login = "testuser";
        User expectedUser = new User();
        when(repository.findByLogin(login)).thenReturn(expectedUser);

        // Quando
        User user = (User) authService.loadUserByUsername(login);

        // Então
        assertNotNull(user);
        assertEquals(expectedUser, user);
    }




}
