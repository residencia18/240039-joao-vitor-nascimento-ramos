import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrimeiraCategoriaComponent } from './primeira-categoria.component';

describe('PrimeiraCategoriaComponent', () => {
  let component: PrimeiraCategoriaComponent;
  let fixture: ComponentFixture<PrimeiraCategoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PrimeiraCategoriaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PrimeiraCategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
