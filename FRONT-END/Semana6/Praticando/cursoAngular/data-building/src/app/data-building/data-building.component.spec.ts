import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataBuildingComponent } from './data-building.component';

describe('DataBuildingComponent', () => {
  let component: DataBuildingComponent;
  let fixture: ComponentFixture<DataBuildingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DataBuildingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DataBuildingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
