import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RouterWikiComponent } from './router-wiki.component';

describe('RouterWikiComponent', () => {
  let component: RouterWikiComponent;
  let fixture: ComponentFixture<RouterWikiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RouterWikiComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RouterWikiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
