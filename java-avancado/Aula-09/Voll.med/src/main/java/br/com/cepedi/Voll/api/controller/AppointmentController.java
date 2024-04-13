package br.com.cepedi.Voll.api.controller;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataCancelAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.output.DataDetailsAppointment;
import br.com.cepedi.Voll.api.services.appointment.AppointmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    private static final Logger log = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> schedule(@RequestBody @Valid DataRegisterAppointment data) {
        log.info("Scheduling new appointment...");
        DataDetailsAppointment details = service.register(data);
        log.info("Appointment scheduled successfully.");
        return ResponseEntity.ok(details);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Object> cancel(@RequestBody @Valid DataCancelAppointment data) {
        log.info("Cancelling appointment...");
        service.cancel(data);
        log.info("Appointment cancelled successfully.");
        return ResponseEntity.noContent().build();
    }

}