package br.com.cepedi.Voll.api.services.patient.validations.disabled;

import br.com.cepedi.Voll.api.repository.PatientRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.Random.class)
public class ValidatePatientAlreadyDisabledTest {

    @Mock
    private PatientRepository repository;

    @InjectMocks
    private ValidatePatientAlreadyDisabled validatePatientAlreadyDisabled;

    @BeforeEach
    void setup(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("Disable patient - Patient already disabled")
    public void shouldThrowExceptionWhenAlreadyDisabled() {
        // Given
        when(repository.findActivatedById(anyLong())).thenReturn(false);
        String expectedException  = "The required patient already disabled";

        // When & Then
        Exception exception = assertThrows(ValidationException.class, () -> validatePatientAlreadyDisabled.validation(anyLong()));
        assertEquals(expectedException,exception.getMessage());
    }

}
