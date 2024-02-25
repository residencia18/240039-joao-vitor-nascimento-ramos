import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SegundaCategoriaComponent } from './segunda-categoria.component';

describe('SegundaCategoriaComponent', () => {
  let component: SegundaCategoriaComponent;
  let fixture: ComponentFixture<SegundaCategoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SegundaCategoriaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SegundaCategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
