import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RouterReaderComponent } from './router-reader.component';

describe('RouterReaderComponent', () => {
  let component: RouterReaderComponent;
  let fixture: ComponentFixture<RouterReaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RouterReaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RouterReaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
