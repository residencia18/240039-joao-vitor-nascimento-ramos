package br.com.cepedi.Voll.api.controller;

import br.com.cepedi.Voll.api.model.records.appointment.input.DataCancelAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.input.DataRegisterAppointment;
import br.com.cepedi.Voll.api.model.records.appointment.output.DataDetailsAppointment;
import br.com.cepedi.Voll.api.services.appointment.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid DataRegisterAppointment data) {
        DataDetailsAppointment details = service.register(data);
        return ResponseEntity.ok(details);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid DataCancelAppointment data) {
        service.cancel(data);
        return ResponseEntity.noContent().build();
    }

}