package br.com.cepedi.Voll.api.services.doctor.validations.update;

import br.com.cepedi.Voll.api.model.records.doctor.input.DataUpdateDoctor;
import br.com.cepedi.Voll.api.model.records.patient.input.DataUpdatePatient;
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
public class ValidateDoctorExistenceForUpdateTest {

    @Mock
    private DoctorRepository repository;

    @InjectMocks
    private ValidateDoctorExistenceForUpdate validateDoctorExistenceForUpdate;

    @BeforeEach
    void setup(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("Update patient - Patient not does exists")
    public void shouldThrowExceptionWheDisabledForUpdate() {
        // Given
        when(repository.existsById(anyLong())).thenReturn(false);
        String expectedException  = "The required doctor is does not exists";


        // When & Then
        Exception exception = assertThrows(ValidationException.class, () -> validateDoctorExistenceForUpdate.validation(anyLong(), new DataUpdateDoctor(null,null,null,null)));
        assertEquals(expectedException,exception.getMessage());

    }
}
