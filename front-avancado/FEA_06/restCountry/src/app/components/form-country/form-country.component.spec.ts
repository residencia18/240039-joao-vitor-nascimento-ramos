import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormCountryComponent } from './form-country.component';

describe('FormCountryComponent', () => {
  let component: FormCountryComponent;
  let fixture: ComponentFixture<FormCountryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FormCountryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FormCountryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
