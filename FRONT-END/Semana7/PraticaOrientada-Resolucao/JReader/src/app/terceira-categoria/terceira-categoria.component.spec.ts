import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TerceiraCategoriaComponent } from './terceira-categoria.component';

describe('TerceiraCategoriaComponent', () => {
  let component: TerceiraCategoriaComponent;
  let fixture: ComponentFixture<TerceiraCategoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TerceiraCategoriaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TerceiraCategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
