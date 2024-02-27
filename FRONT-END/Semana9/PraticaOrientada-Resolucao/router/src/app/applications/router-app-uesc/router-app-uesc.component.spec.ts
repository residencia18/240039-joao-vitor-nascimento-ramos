import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RouterAppUescComponent } from './router-app-uesc.component';

describe('RouterAppUescComponent', () => {
  let component: RouterAppUescComponent;
  let fixture: ComponentFixture<RouterAppUescComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RouterAppUescComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RouterAppUescComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
