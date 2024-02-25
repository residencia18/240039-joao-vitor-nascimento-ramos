import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElvisComponent } from './elvis.component';

describe('ElvisComponent', () => {
  let component: ElvisComponent;
  let fixture: ComponentFixture<ElvisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ElvisComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ElvisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
