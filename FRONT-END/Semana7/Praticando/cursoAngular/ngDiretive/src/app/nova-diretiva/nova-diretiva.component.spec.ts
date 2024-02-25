import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NovaDiretivaComponent } from './nova-diretiva.component';

describe('NovaDiretivaComponent', () => {
  let component: NovaDiretivaComponent;
  let fixture: ComponentFixture<NovaDiretivaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NovaDiretivaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NovaDiretivaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
