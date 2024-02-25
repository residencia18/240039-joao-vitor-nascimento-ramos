import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UltimaCategoriaComponent } from './ultima-categoria.component';

describe('UltimaCategoriaComponent', () => {
  let component: UltimaCategoriaComponent;
  let fixture: ComponentFixture<UltimaCategoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UltimaCategoriaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UltimaCategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
