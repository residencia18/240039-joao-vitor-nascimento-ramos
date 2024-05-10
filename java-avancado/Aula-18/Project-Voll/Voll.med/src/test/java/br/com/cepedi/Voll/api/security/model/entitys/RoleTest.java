package br.com.cepedi.Voll.api.security.model.entitys;

import br.com.cepedi.Voll.api.security.model.entitys.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoleTest {

    @Test
    @DisplayName("Test creation of Role entity")
    public void testRoleCreation() {
        // Dado
        String roleName = "ROLE_ADMIN";

        // Quando
        Role role = new Role(roleName);

        // Então
        assertNotNull(role);
        assertEquals(roleName, role.getName());
    }

    @Test
    @DisplayName("Test setting and getting users for Role")
    public void testSetAndGetUsers() {
        // Dado
        Role role = new Role();
        Set<User> users = new HashSet<>();
        User user1 = new User();
        User user2 = new User();
        users.add(user1);
        users.add(user2);

        // Quando
        role.setUsers(users);

        // Então
        assertEquals(users, role.getUsers());
    }
}



