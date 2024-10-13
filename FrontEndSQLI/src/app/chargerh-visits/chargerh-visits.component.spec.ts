import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChargerhVisitsComponent } from './chargerh-visits.component';

describe('ChargerhVisitsComponent', () => {
  let component: ChargerhVisitsComponent;
  let fixture: ComponentFixture<ChargerhVisitsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChargerhVisitsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChargerhVisitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
