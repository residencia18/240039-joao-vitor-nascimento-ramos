import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioComumComponent } from './formulario-comum.component';

describe('FormularioComumComponent', () => {
  let component: FormularioComumComponent;
  let fixture: ComponentFixture<FormularioComumComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormularioComumComponent]
    });
    fixture = TestBed.createComponent(FormularioComumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
