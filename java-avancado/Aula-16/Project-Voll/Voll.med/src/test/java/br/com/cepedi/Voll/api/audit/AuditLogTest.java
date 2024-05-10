package br.com.cepedi.Voll.api.audit;

import br.com.cepedi.Voll.api.audit.entitys.AuditLog;
import br.com.cepedi.Voll.api.audit.record.DataRegisterAudit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class AuditLogTest {

    private static AuditLog auditLog;

    @BeforeAll
    public static void setUp() {
        auditLog = new AuditLog();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(auditLog);
    }

    @Test
    @DisplayName("Test constructor with Data")
    public void testCustomConstructor() {
        DataRegisterAudit dataRegisterAudit = new DataRegisterAudit(
                "Event",
                "Description",
                1L,
                "User",
                "Resource",
                "Origin"
        );

        auditLog = new AuditLog(dataRegisterAudit);

        assertEquals("Event", auditLog.getEventName());
        assertEquals("Description", auditLog.getEventDescription());
        assertNotNull(auditLog.getTimestamp());
        assertEquals(1L, auditLog.getUserId().longValue());
        assertEquals("User", auditLog.getUserName());
        assertEquals("Resource", auditLog.getAffectedResource());
        assertEquals("Origin", auditLog.getOrigin());
    }

    @Test
    @DisplayName("Test setters and getters methods")
    public void testSetters() {
        auditLog.setEventName("NewEvent");
        assertEquals("NewEvent", auditLog.getEventName());

        auditLog.setEventDescription("NewDescription");
        assertEquals("NewDescription", auditLog.getEventDescription());

        Long newUserId = 2L;
        auditLog.setUserId(newUserId);
        assertEquals(newUserId, auditLog.getUserId());

        auditLog.setUserName("NewUser");
        assertEquals("NewUser", auditLog.getUserName());

        auditLog.setAffectedResource("NewResource");
        assertEquals("NewResource", auditLog.getAffectedResource());

        auditLog.setOrigin("NewOrigin");
        assertEquals("NewOrigin", auditLog.getOrigin());
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String eventName = "Event";
        String eventDescription = "Description";
        Date timestamp = new Date();
        Long userId = 1L;
        String userName = "User";
        String affectedResource = "Resource";
        String origin = "Origin";

        AuditLog auditLog = new AuditLog(id, eventName, eventDescription, timestamp, userId, userName, affectedResource, origin);

        assertNotNull(auditLog);
        assertEquals(id, auditLog.getId());
        assertEquals(eventName, auditLog.getEventName());
        assertEquals(eventDescription, auditLog.getEventDescription());
        assertEquals(timestamp, auditLog.getTimestamp());
        assertEquals(userId, auditLog.getUserId());
        assertEquals(userName, auditLog.getUserName());
        assertEquals(affectedResource, auditLog.getAffectedResource());
        assertEquals(origin, auditLog.getOrigin());
    }

    @Test
    public void testEqualsAndHashCode() {
        Long id = 1L;
        String eventName = "Event";
        String eventDescription = "Description";
        Date timestamp = new Date();
        Long userId = 1L;
        String userName = "User";
        String affectedResource = "Resource";
        String origin = "Origin";

        AuditLog auditLog1 = new AuditLog(id, eventName, eventDescription, timestamp, userId, userName, affectedResource, origin);
        AuditLog auditLog2 = new AuditLog(id, eventName, eventDescription, timestamp, userId, userName, affectedResource, origin);

        // Teste se os objetos são iguais
        assertEquals(auditLog1, auditLog2);

        // Teste se os códigos de hash são iguais
        assertEquals(auditLog1.hashCode(), auditLog2.hashCode());

        // Alterar um atributo
        auditLog2.setEventName("NewEvent");

        // Teste se os objetos são diferentes após a alteração
        assertNotEquals(auditLog1, auditLog2);
    }

}