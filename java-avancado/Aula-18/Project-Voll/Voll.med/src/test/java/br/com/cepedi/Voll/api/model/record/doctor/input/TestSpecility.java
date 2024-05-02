package br.com.cepedi.Voll.api.model.record.doctor.input;

import br.com.cepedi.Voll.api.model.records.doctor.input.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSpecility {

    @Test
    @DisplayName("Test Specialty enum values")
    public void testSpecialtyEnumValues() {
        // Verificar se os valores do enum estão definidos corretamente
        assertNotNull(Specialty.ORTHOPEDICS);
        assertNotNull(Specialty.CARDIOLOGY);
        assertNotNull(Specialty.GYNECOLOGY);
        assertNotNull(Specialty.DERMATOLOGY);
    }

    @Test
    @DisplayName("Test Specialty enum string values")
    public void testSpecialtyEnumStringValues() {
        // Verificar se os valores do enum estão com os nomes corretos
        assertEquals("ORTHOPEDICS", Specialty.ORTHOPEDICS.name());
        assertEquals("CARDIOLOGY", Specialty.CARDIOLOGY.name());
        assertEquals("GYNECOLOGY", Specialty.GYNECOLOGY.name());
        assertEquals("DERMATOLOGY", Specialty.DERMATOLOGY.name());
    }

}
