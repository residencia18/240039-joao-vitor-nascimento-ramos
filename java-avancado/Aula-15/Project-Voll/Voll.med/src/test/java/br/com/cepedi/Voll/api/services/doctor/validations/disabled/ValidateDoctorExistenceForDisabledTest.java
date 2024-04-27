package br.com.cepedi.Voll.api.services.doctor.validations.disabled;

import br.com.cepedi.Voll.api.repository.DoctorRepository;
import br.com.cepedi.Voll.api.services.doctor.DoctorService;
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
public class ValidateDoctorExistenceForDisabledTest {

    @Mock
    private DoctorRepository repository;

    @InjectMocks
    private ValidateDoctorExistenceForDisabled validateDoctorExistenceForDisabled;

    @BeforeEach
    void setup(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("Disable doctor - Patient does not exist")
    public void shouldThrowExceptionWhenDisablingNonExistingDoctor() {
        // Given
        when(repository.existsById(anyLong())).thenReturn(false);
        String expectedException  = "The required doctor is does not exists";

        // When & Then
        Exception exception = assertThrows(ValidationException.class, () -> validateDoctorExistenceForDisabled.validation(anyLong()));
        assertEquals(expectedException,exception.getMessage());
    }

}
