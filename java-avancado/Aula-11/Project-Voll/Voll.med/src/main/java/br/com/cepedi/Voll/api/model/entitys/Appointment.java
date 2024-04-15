package br.com.cepedi.Voll.api.model.entitys;

import br.com.cepedi.Voll.api.model.records.appointment.input.ReasonCancelAppointment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "appointments")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime dateService;

    @Column(name = "reason_cancel")
    @Enumerated(EnumType.STRING)
    private ReasonCancelAppointment reasonCancel;

    public void cancel(ReasonCancelAppointment reason) {
        this.reasonCancel = reason;
    }

}