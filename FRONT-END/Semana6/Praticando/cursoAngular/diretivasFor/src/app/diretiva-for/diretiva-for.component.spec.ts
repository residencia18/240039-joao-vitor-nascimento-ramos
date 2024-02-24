import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiretivaForComponent } from './diretiva-for.component';

describe('DiretivaForComponent', () => {
  let component: DiretivaForComponent;
  let fixture: ComponentFixture<DiretivaForComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DiretivaForComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DiretivaForComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
