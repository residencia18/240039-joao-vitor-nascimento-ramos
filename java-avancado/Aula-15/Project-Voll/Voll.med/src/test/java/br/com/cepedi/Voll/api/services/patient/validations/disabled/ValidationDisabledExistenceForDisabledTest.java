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
public class ValidationDisabledExistenceForDisabledTest {


    @Mock
    private PatientRepository repository;

    @InjectMocks
    ValidateDisabledExistenceForDisabled validateDisabledExistenceForDisabled;

    @BeforeEach
    void setup(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("Disable patient - Patient does not exist")
    public void shouldThrowExceptionWhenDisablingNonExistingPatient() {
        // Given
        when(repository.existsById(anyLong())).thenReturn(false);
        String expectedException  = "The required patient is does not exists";

        // When & Then
        Exception exception = assertThrows(ValidationException.class, () -> validateDisabledExistenceForDisabled.validation(anyLong()));
        assertEquals(expectedException,exception.getMessage());
    }
}
