package br.com.cepedi.Voll.api.security.model.entitys;


import br.com.cepedi.Voll.api.security.model.records.input.DataRegisterUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    @DisplayName("Test creation of User entity")
    public void testUserCreation() {
        // Dado
        String login = "testuser";
        String email = "testuser@example.com";
        String name = "Test User";
        String password = "password123";
        DataRegisterUser dataRegisterUser = new DataRegisterUser(login, email, name, password);

        // Quando
        User user = new User(dataRegisterUser, passwordEncoder);

        // Então
        assertNotNull(user);
        assertEquals(login, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(name, user.getName());
        assertTrue(passwordEncoder.matches(password, user.getPassword()));
    }

    @Test
    @DisplayName("Test setting and getting roles for User")
    public void testSetAndGetRoles() {
        // Dado
        User user = new User();
        Set<Role> roles = new HashSet<>();
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        roles.add(role1);
        roles.add(role2);

        // Quando
        user.setRoles(roles);

        // Então
        assertEquals(roles, user.getRoles());
    }

    @Test
    @DisplayName("Test getting authorities for User")
    public void testGetAuthorities() {
        // Dado
        User user = new User();
        Set<Role> roles = new HashSet<>();
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");
        roles.add(role1);
        roles.add(role2);
        user.setRoles(roles);

        // Quando
        Set<String> expectedAuthorities = Set.of("ROLE_ADMIN");

        // Então
        assertEquals(expectedAuthorities, user.getAuthorities().stream().map(Object::toString).collect(Collectors.toSet()));
    }
}