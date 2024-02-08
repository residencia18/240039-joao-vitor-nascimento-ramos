import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormReativoComponent } from './form-reativo.component';

describe('FormReativoComponent', () => {
  let component: FormReativoComponent;
  let fixture: ComponentFixture<FormReativoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormReativoComponent]
    });
    fixture = TestBed.createComponent(FormReativoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
