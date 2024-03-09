import { ServicoDadosAtendimento } from './appointment-data.service';
import { TestBed } from '@angular/core/testing';


describe('AppointmentDataService', () => {
  let service: ServicoDadosAtendimento;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicoDadosAtendimento);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
